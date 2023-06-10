package com.example.webs2023.controller;

import com.example.webs2023.base.BaseController;
import com.example.webs2023.base.ServiceLocator;
import com.example.webs2023.base.Response;
import com.example.webs2023.dto.user.UserInput;
import com.example.webs2023.service.auth.AuthService;
import com.example.webs2023.utils.JsonFromInputConverter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/auth/register")
public class RegisterController extends BaseController {

    private final AuthService authService = ServiceLocator.getDependency(AuthService.class);


    @Override
    protected Response getMethod(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    protected Response postMethod(HttpServletRequest request, HttpServletResponse response) {
        try {
            UserInput userInput = GSON.fromJson(JsonFromInputConverter.getInputStream(request.getInputStream()), UserInput.class);
            userInput.setRole("USER");
            return Response.success(authService.register(userInput));
        } catch (Exception e) {
            return new Response("error", e.getMessage(), e);
        }
    }

    @Override
    protected Response putMethod(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    protected Response deleteMethod(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
