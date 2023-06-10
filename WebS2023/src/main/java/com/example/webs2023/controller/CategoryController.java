package com.example.webs2023.controller;

import com.example.webs2023.base.BaseController;
import com.example.webs2023.base.Response;
import com.example.webs2023.base.ServiceLocator;
import com.example.webs2023.dto.category.CategoryInput;
import com.example.webs2023.service.category.CategoryService;
import com.example.webs2023.utils.JsonFromInputConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/api/categories")
public class CategoryController extends BaseController {

    @Override
    public void init() throws ServletException {
        super.init();
        service = ServiceLocator.getDependency(CategoryService.class);
    }

    @Override
    protected Response getMethod(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
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
        try {
            CategoryInput categoryInput = GSON.fromJson(JsonFromInputConverter.getInputStream(request.getInputStream()), CategoryInput.class);
            return new Response("success", "Thanh Cong", service.save(categoryInput));
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("fail", "That bai", e.getStackTrace());
        }
    }

    @Override
    protected Response putMethod(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter("id");
            CategoryInput categoryInput = GSON.fromJson(JsonFromInputConverter.getInputStream(request.getInputStream()), CategoryInput.class);
            if (id != null && !id.isEmpty()) {
                return new Response("success", "Thanh Cong", service.updateById(Long.parseLong(id), categoryInput));
            } else {
                return new Response("fail", "That bai", "id is required");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("fail", "That bai", e.getStackTrace());
        }
    }

    @Override
    protected Response deleteMethod(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                return new Response("success", "Thanh Cong", service.deleteById(Long.parseLong(id)));
            } else {
                return new Response("fail", "That bai", "id is required");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("fail", "That bai", e.getStackTrace());
        }
    }
}
