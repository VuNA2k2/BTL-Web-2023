package com.example.webs2023.controller;

import com.example.webs2023.base.BaseController;
import com.example.webs2023.base.Response;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/api/orders")
public class OrderController extends BaseController {
    @Override
    protected Response getMethod(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    protected Response postMethod(HttpServletRequest request, HttpServletResponse response) {
        return null;
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
