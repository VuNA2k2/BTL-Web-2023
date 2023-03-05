package com.example.webs2023.controller;

import com.example.webs2023.base.Response;
import com.example.webs2023.config.DatabaseConnection;
import com.example.webs2023.dao.Todos;
import com.example.webs2023.model.User;
import com.example.webs2023.ulti.JsonFromInputConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.sql.*;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/todos")
public class TodoController extends HttpServlet {
    private final Gson GSON = new GsonBuilder().create();
    private Connection connection;

    @Override
    public void init() throws ServletException {
        try {
            connection = DatabaseConnection.getInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        Long id = Long.parseLong(request.getParameter("id"));

//        String json = GSON.toJson(Todos.todos.get(id));

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            response.setStatus(200);
            response.setHeader("Content-Type", "application/json");
            if (resultSet.next()) {
                response.getOutputStream().println(GSON.toJson(new User(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("date_of_birth"))));
            } else {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, IllegalStateException {

        resp.setHeader("Content-Type", "application/json");
        try {
            User user = GSON.fromJson(JsonFromInputConverter.getInputStream(req.getReader()), User.class);
            PreparedStatement st = connection.prepareStatement("insert into users values(?, ?)");
            st.setString(2, user.getName());
            st.setString(1, user.getDateOfBirth());
            st.execute();
            st.close();
            connection.close();
            resp.setStatus(201);

            resp.getOutputStream().println(GSON.toJson(user));
        } catch (SQLException e) {
            resp.setStatus(400);
            resp.getOutputStream().println(e.getMessage());

        }

    }

    public void destroy() {
    }
}