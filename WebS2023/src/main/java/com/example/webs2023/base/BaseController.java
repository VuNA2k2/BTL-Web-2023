package com.example.webs2023.base;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseController<E, T, I, O> extends HttpServlet {

    protected BaseService<E, T, I, O> service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(200);
        resp.setHeader("Content-Type", "application/json");
        resp.getOutputStream().println(getMethod(req, resp).toJson());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(200);
        resp.setHeader("Content-Type", "application/json");
        resp.getOutputStream().println(postMethod(req, resp).toJson());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(200);
        resp.setHeader("Content-Type", "application/json");
        resp.getOutputStream().println(putMethod(req, resp).toJson());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(200);
        resp.setHeader("Content-Type", "application/json");
        resp.getOutputStream().println(deleteMethod(req, resp).toJson());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    protected abstract Response<O> getMethod(HttpServletRequest request, HttpServletResponse response);

    protected abstract Response<O> postMethod(HttpServletRequest request, HttpServletResponse response);

    protected abstract Response<O> putMethod(HttpServletRequest request, HttpServletResponse response);

    protected abstract Response<O> deleteMethod(HttpServletRequest request, HttpServletResponse response);
}

