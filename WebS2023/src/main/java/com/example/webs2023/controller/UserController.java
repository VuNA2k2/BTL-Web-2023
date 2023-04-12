package com.example.webs2023.controller;

import com.example.webs2023.base.BaseController;
import com.example.webs2023.base.DependencyInjector;
import com.example.webs2023.base.Response;
import com.example.webs2023.dto.user.UserInput;
import com.example.webs2023.dto.user.UserOutput;
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

    @Override
    public void init() throws ServletException {
        super.init();
        service = DependencyInjector.getDependency(UserService.class);
    }


    @Override
    protected Response getMethod(HttpServletRequest request, HttpServletResponse response) {
        try {
            return new Response("success", "Thanh Cong", service.getById(Long.parseLong(request.getParameter("id"))));
//            TODO: Using more methods here and return result
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            return new Response("fail", "That bai", null);
        }
    }

    @Override
    protected Response postMethod(HttpServletRequest request, HttpServletResponse response) {
        try {
            UserInput userInput = GSON.fromJson(JsonFromInputConverter.getInputStream(request.getReader()), UserInput.class);
            UserOutput userOutput = (UserOutput) service.save(userInput);
            return new Response("success", "Thanh Cong", userOutput);
//            TODO: Using more methods here and return result
        } catch (IOException | SQLException | InvocationTargetException | NoSuchMethodException |
                 InstantiationException | IllegalAccessException e) {
            return new Response("fail", "That bai", null);
        }
    }

    @Override
    protected Response putMethod(HttpServletRequest request, HttpServletResponse response) {
        try {
            UserInput userInput = GSON.fromJson(JsonFromInputConverter.getInputStream(request.getReader()), UserInput.class);
            UserOutput userOutput = (UserOutput) service.updateById(Long.parseLong(request.getParameter("id")), userInput);
            return new Response("success", "Thanh Cong", userOutput);
//        TODO: Using more methods here and return result
        } catch (IOException | SQLException | InvocationTargetException | NoSuchMethodException |
                 InstantiationException | IllegalAccessException e) {
            return new Response("fail", "That bai", null);
        }
    }

    @Override
    protected Response deleteMethod(HttpServletRequest request, HttpServletResponse response) {
        try {
            return new Response("success", "Thanh Cong", service.deleteById(Long.parseLong(request.getParameter("id"))));
//        TODO: Using more methods here and return result
        } catch (SQLException e) {
            return new Response("fail", "That bai", null);
        }
    }
}