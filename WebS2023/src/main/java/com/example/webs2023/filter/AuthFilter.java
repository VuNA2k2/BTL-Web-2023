package com.example.webs2023.filter;

import com.example.webs2023.base.DependencyInjector;
import com.example.webs2023.service.jwt.JwtService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    private final JwtService jwtService = DependencyInjector.getDependency(JwtService.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        String path = requestURI.substring(contextPath.length());
        System.out.println(path);
        if (!path.startsWith("/api")) return;
        String requireRole = getRequireRole(path, httpRequest.getMethod());
        if (requireRole.equals("NONE")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String authHeader = httpRequest.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            String token = authHeader.substring(7);
            try {
                if (jwtService.validateToken(token, requireRole)) {
                    httpRequest.setAttribute("payload", jwtService.getPayload(token));
                } else {
                    httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (Exception e) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
    }

    private String getRequireRole(String path, String method) {
        if (path.startsWith("/api/auth")) return "NONE";
        else if (path.startsWith("/api/products")) {
            if (method.equals("POST") || method.equals("PUT") || method.equals("DELETE")) return "ADMIN";
            else return "NONE";
        } else if (path.startsWith("/api/carts")) return "USER";
        else if (path.startsWith("/api/orders")) {
            if (method.equals("GET") || method.equals("PUT")) return "BOTH";
            else if(method.equals("POST")) return "USER";
            else return "ADMIN";
        } else if(path.startsWith("/api/users")) {
            if(method.equals("GET")) return "BOTH";
            else if(method.equals("POST")) return "NONE";
            else if(method.equals("PUT")) return "BOTH";
            else if(method.equals("DELETE")) return "ADMIN";
            else return "NONE";
        } else if(path.startsWith("/api/rates")) {
            if(method.equals("POST")) return "USER";
            else return "NONE";
        }
        else return "NONE";
    }

    @Override
    public void destroy() {

    }
}
