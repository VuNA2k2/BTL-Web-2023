package com.example.webs2023.controller;

import com.example.webs2023.base.BaseController;
import com.example.webs2023.base.DependencyInjector;
import com.example.webs2023.base.Response;
import com.example.webs2023.service.product.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet(value = "/products")
public class ProductController extends BaseController {
    @Override
    public void init() throws ServletException {
        super.init();
        service = DependencyInjector.getDependency(ProductService.class);
    }

    @Override
    protected Response getMethod(HttpServletRequest request, HttpServletResponse response) {
        try {
            if(request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
                return new Response("success", "Thanh Cong", service.getById(Long.parseLong(request.getParameter("id"))));
            } else {
                return new Response("success", "Thanh Cong", service.getAll());
            }
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                IllegalAccessException e) {
            return new Response("fail", "That bai", e);
        }
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
