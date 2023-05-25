package com.example.webs2023.filter;

import com.example.webs2023.base.DependencyInjector;
import com.example.webs2023.service.jwt.JwtService;
import com.example.webs2023.service.jwt.JwtServiceImpl;

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
        if(!path.startsWith("/api")) return;
        if(isAuthNotRequired(path, httpRequest.getMethod())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String authHeader = httpRequest.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            String token = authHeader.substring(7);
            try {
                if(jwtService.validateToken(token)) {
                    httpRequest.setAttribute("payload", jwtService.getPayload(token));
                } else {
                    httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                };
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (Exception e) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
    }

    private boolean isAuthNotRequired(String path, String method) {
       if(path.startsWith("/api/login")) return true;
       else if(method.equals("GET") && path.startsWith("/api/products")) return true;
       else return false;
    }
    @Override
    public void destroy() {

    }
}
