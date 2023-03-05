package com.example.webs2023.controller;

import com.example.webs2023.base.Response;
import com.example.webs2023.config.DatabaseConnection;
import com.example.webs2023.entity.UserEntity;
import com.example.webs2023.utils.JsonFromInputConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(value = "/todos")
public class UserController extends HttpServlet {
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
                response.getOutputStream().println(GSON.toJson(new Response<>("success", " Thành công", new UserEntity(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("date_of_birth")))));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, IllegalStateException {

        resp.setHeader("Content-Type", "application/json");
        try {
            UserEntity userEntity = GSON.fromJson(JsonFromInputConverter.getInputStream(req.getReader()), UserEntity.class);
            PreparedStatement st = connection.prepareStatement("insert into users values(?, ?)");
            st.setString(2, userEntity.getName());
            st.setString(1, userEntity.getDateOfBirth());
            st.execute();
            st.close();
            resp.setStatus(201);
            resp.getOutputStream().println(GSON.toJson(userEntity));
        } catch (SQLException e) {
            resp.setStatus(400);
            resp.getOutputStream().println(e.getMessage());

        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }
}