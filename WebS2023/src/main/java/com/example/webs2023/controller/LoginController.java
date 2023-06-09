package com.example.webs2023.controller;

import com.example.webs2023.base.BaseController;
import com.example.webs2023.base.ServiceLocator;
import com.example.webs2023.base.Response;
import com.example.webs2023.dto.login.LoginInput;
import com.example.webs2023.service.auth.AuthService;
import com.example.webs2023.utils.JsonFromInputConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/api/auth/login")
public class LoginController extends BaseController {
    private final AuthService authService = ServiceLocator.getDependency(AuthService.class);

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected Response getMethod(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    protected Response postMethod(HttpServletRequest request, HttpServletResponse response) {
        try {
            LoginInput loginInput = GSON.fromJson(JsonFromInputConverter.getInputStream(request.getReader()), LoginInput.class);
            return new Response(
                    "success",
                    "Thanh cong",
                    authService.login(loginInput));
        } catch (Exception e) {
            return new Response("fail", "That bai", e);
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
