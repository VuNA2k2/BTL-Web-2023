package com.example.webs2023.controller;

import com.example.webs2023.dao.Todos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private final Gson GSON = new GsonBuilder().create();
    public void init() {
        message = "Hello VuNA!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        System.out.println(uri);
//        Long id = Long.parseLong(uri.substring("/todos/".length()));

        String json = GSON.toJson(Todos.todos.get(1L));

        response.setStatus(200);
        response.setHeader("Content-Type", "application/json");
        response.getOutputStream().println(json);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    public void destroy() {
    }
}