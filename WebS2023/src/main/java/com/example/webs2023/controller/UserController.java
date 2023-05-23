package com.example.webs2023.controller;

import com.example.webs2023.base.BaseController;
import com.example.webs2023.base.DependencyInjector;
import com.example.webs2023.base.Response;
import com.example.webs2023.dto.jwt.JwtPayload;
import com.example.webs2023.dto.user.UserInput;
import com.example.webs2023.dto.user.UserOutput;
import com.example.webs2023.service.jwt.JwtService;
import com.example.webs2023.service.user.UserService;
import com.example.webs2023.service.user.UserServiceImpl;
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
    JwtService jwtService = DependencyInjector.getDependency(JwtService.class);
    UserService service = DependencyInjector.getDependency(UserService.class);
    @Override
    public void init() throws ServletException {
        super.init();

    }


    @Override
    protected Response getMethod(HttpServletRequest request, HttpServletResponse response) {
        try {
            String token = request.getHeader("Authorization").substring(7);
            JwtPayload jwtPayload = jwtService.getPayload(token);
            UserOutput userOutput = service.getUserById(jwtPayload.getUserId());
            if (userOutput == null) {
                response.setStatus(401);
                return new Response("UNAUTHORIZED", "Chưa đăng nhập vui lòng đăng nhập", null);
            } else if (!userOutput.getRole().equals("ADMIN")) {
                response.setStatus(403);
                return new Response("FORBIDDEN", "Không có quyền truy cập", null);
            }
            if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
                return new Response("success", "Thanh Cong", service.getUserById(Long.parseLong(request.getParameter("id"))));
            } else if (request.getParameter("role") != null && !request.getParameter("role").isEmpty()) {
                return new Response("success", "Thanh Cong", service.getUserByRole(request.getParameter("role")));
            } else if (request.getParameter("username") != null && !request.getParameter("username").isEmpty()) {
                return new Response("success", "Thanh Cong", service.getUserByUsername(request.getParameter("username")));
            } else if (request.getParameter("email") != null && !request.getParameter("email").isEmpty()) {
                return new Response("success", "Thanh Cong", service.getUserByEmail(request.getParameter("email")));
            } else if (request.getParameter("phone") != null && !request.getParameter("phone").isEmpty()) {
                return new Response("success", "Thanh Cong", service.getUserByPhone(request.getParameter("phone")));
            } else {
                return Response.success(userOutput);
            }
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
            UserOutput userOutput = (UserOutput) service.saveUser(userInput);
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