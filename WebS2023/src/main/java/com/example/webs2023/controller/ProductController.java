package com.example.webs2023.controller;

import com.example.webs2023.base.BaseController;
import com.example.webs2023.base.DependencyInjector;
import com.example.webs2023.base.Response;
import com.example.webs2023.service.product.ProductService;
import com.example.webs2023.service.product.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet(value = "/api/products")
public class ProductController extends BaseController {
    @Override
    public void init() throws ServletException {
        super.init();
        service = (ProductServiceImpl) DependencyInjector.getDependency(ProductService.class);
    }

    @Override
    protected Response getMethod(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter("id");
            String categoryId = request.getParameter("categoryId");
            if (id != null && !id.isBlank()) {
                return Response.success(((ProductService) service).getProductById(Long.parseLong(id)));
            } else if(categoryId != null && !categoryId.isBlank()) {
                return Response.success(((ProductService) service).getProductByCategoryId(Long.parseLong(categoryId)));
            }else {
                return Response.success(((ProductService) service).getAllProduct());
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
