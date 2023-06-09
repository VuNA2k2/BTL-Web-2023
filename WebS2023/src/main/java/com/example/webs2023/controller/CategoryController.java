package com.example.webs2023.controller;

import com.example.webs2023.base.BaseController;
import com.example.webs2023.base.ServiceLocator;
import com.example.webs2023.base.Response;
import com.example.webs2023.service.category.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(value = "/categories")
public class CategoryController extends BaseController {

    @Override
    public void init() throws ServletException {
        super.init();
        service = ServiceLocator.getDependency(CategoryService.class);
    }

    @Override
    protected Response getMethod(HttpServletRequest request, HttpServletResponse response) {
        try {
            if(request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
                return new Response("success", "Thanh Cong", service.getById(Long.parseLong(request.getParameter("id"))));
            } else {
                return new Response("success", "Thanh Cong", service.getAll());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("fail", "That bai", e.getStackTrace());
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
