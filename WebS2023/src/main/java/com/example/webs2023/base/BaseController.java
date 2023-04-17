package com.example.webs2023.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseController<E, T, I, O> extends HttpServlet {

    protected BaseService<E, T, I, O> service;

    protected Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String result = getMethod(req, resp).toJson();
        resp.getWriter().print(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String result = postMethod(req, resp).toJson();
        resp.getWriter().print(result);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String result = putMethod(req, resp).toJson();
        resp.getWriter().print(result);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String result = deleteMethod(req, resp).toJson();
        resp.getWriter().print(result);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setHeader("Access-Control-Max-Age", "86400");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(200);
        super.service(req, resp);
    }

    protected abstract Response<O> getMethod(HttpServletRequest request, HttpServletResponse response);

    protected abstract Response<O> postMethod(HttpServletRequest request, HttpServletResponse response);

    protected abstract Response<O> putMethod(HttpServletRequest request, HttpServletResponse response);

    protected abstract Response<O> deleteMethod(HttpServletRequest request, HttpServletResponse response);
}

