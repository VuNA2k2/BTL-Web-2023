package com.example.webs2023.controller;

import com.example.webs2023.base.BaseController;
import com.example.webs2023.base.DependencyInjector;
import com.example.webs2023.base.Response;
import com.example.webs2023.dto.jwt.JwtPayload;
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

@WebServlet(value = "/api/users")
public class UserController extends BaseController {
    UserService service = DependencyInjector.getDependency(UserService.class);

    @Override
    public void init() throws ServletException {
        super.init();

    }


    @Override
    protected Response getMethod(HttpServletRequest request, HttpServletResponse response) {
        try {
            JwtPayload jwtPayload = (JwtPayload) request.getAttribute("payload");
            UserOutput userOutput = service.getUserById(jwtPayload.getUserId());
            if (userOutput.getRole().equals("ADMIN")) {
                if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
                    return Response.success(service.getUserById(Long.parseLong(request.getParameter("id"))));
                } else if (request.getParameter("role") != null && !request.getParameter("role").isEmpty()) {
                    return Response.success(service.getUserByRole(request.getParameter("role")));
                } else if (request.getParameter("username") != null && !request.getParameter("username").isEmpty()) {
                    return Response.success(service.getUserByUsername(request.getParameter("username")));
                } else if (request.getParameter("email") != null && !request.getParameter("email").isEmpty()) {
                    return Response.success(service.getUserByEmail(request.getParameter("email")));
                } else if (request.getParameter("phone") != null && !request.getParameter("phone").isEmpty()) {
                    return Response.success(service.getUserByPhone(request.getParameter("phone")));
                } else {
                    return Response.success(service.getAllUsers());
                }
            } else {
                return Response.success(userOutput);
            }
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            return new Response("fail", "That bai", null);
        }
    }

    @Override
    protected Response postMethod(HttpServletRequest request, HttpServletResponse response) {
        try {
            UserInput userInput = GSON.fromJson(JsonFromInputConverter.getInputStream(request.getReader()), UserInput.class);
            UserOutput userOutput = service.saveUser(userInput);
            return Response.success(userOutput);
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
            UserOutput userOutput = (UserOutput) service.updateUser(Long.parseLong(request.getParameter("id")), userInput);
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
            service.deleteUser(Long.parseLong(request.getParameter("id")));
            return Response.success();
//        TODO: Using more methods here and return result
        } catch (SQLException e) {
            return new Response("fail", "That bai", null);
        }
    }
}