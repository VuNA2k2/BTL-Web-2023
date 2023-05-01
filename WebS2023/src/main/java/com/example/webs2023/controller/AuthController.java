package com.example.webs2023.controller;

import com.example.webs2023.base.BaseController;
import com.example.webs2023.base.DependencyInjector;
import com.example.webs2023.base.Response;
import com.example.webs2023.dto.login.LoginInput;
import com.example.webs2023.repository.UserRepository;
import com.example.webs2023.service.auth.AuthService;
import com.example.webs2023.service.jwt.JwtService;
import com.example.webs2023.utils.JsonFromInputConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

@WebServlet(value = "/api/auth/login")
public class AuthController extends BaseController {
    private final String[] publicPaths = {
            "/api/auth/login",
            "/api/auth/register"
    };
    private final AuthService authService = new AuthService(DependencyInjector.getDependency(UserRepository.class));
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected Response getMethod(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getParameter("token");
        try {
            return new Response("success", "Thanh cong", JwtService.validateToken(token));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Response postMethod(HttpServletRequest request, HttpServletResponse response) {
        try {
            String requestURI = request.getRequestURI();
            String contextPath = request.getContextPath();
            String path = requestURI.substring(contextPath.length());
            if(path.equals("/api/auth/login")) {
                LoginInput loginInput = GSON.fromJson(JsonFromInputConverter.getInputStream(request.getReader()), LoginInput.class);
                return new Response(
                        "success",
                        "Thanh cong",
                        authService.login(loginInput));

            } else if(path.equals("/api/auth/register")) {
                return null;
            } else {
                return new Response("fail", "That bai", "Khong tim thay duong dan");
            }
        } catch(Exception e) {
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
