package com.example.webs2023.controller;

import com.example.webs2023.base.BaseController;
import com.example.webs2023.dto.user.UserInput;
import com.example.webs2023.entity.UserEntity;
import com.example.webs2023.repository.UserRepository;
import com.example.webs2023.service.user.UserService;
import com.example.webs2023.utils.JsonFromInputConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet(value = "/users")
public class UserController extends BaseController {

    private UserService service;

    @Override
    public void init() throws ServletException {
        try {
            service = new UserService(new UserRepository(UserEntity.class));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        response.setStatus(200);
        response.setHeader("Content-Type", "application/json");

        try {
            response.getOutputStream().println(GSON.toJson(service.getUserById(id)));
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, IllegalStateException {

        resp.setHeader("Content-Type", "application/json");
        resp.setStatus(201);
        try {
            resp.getOutputStream().println(GSON.toJson(service.save(GSON.fromJson(JsonFromInputConverter.getInputStream(req.getReader()), UserInput.class))));
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }
}