package com.example.webs2023.controller;

import com.example.webs2023.base.BaseController;
import com.example.webs2023.base.DependencyInjector;
import com.example.webs2023.base.Response;
import com.example.webs2023.dto.jwt.JwtPayload;
import com.example.webs2023.dto.order.OrderOutput;
import com.example.webs2023.service.order.OrderService;
import com.example.webs2023.service.order.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/api/orders")
public class OrderController extends BaseController {


    @Override
    public void init() throws ServletException {
        this.service = (OrderServiceImpl) DependencyInjector.getDependency(OrderService.class);
        super.init();
    }

    @Override
    protected Response getMethod(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter("id");
            JwtPayload jwtPayload = (JwtPayload) request.getAttribute("payload");
            return Response.success(((OrderService) this.service).getOrderByUserId(jwtPayload.getUserId()));
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("error", e.getMessage(), e);
        }
    }

    @Override
    protected Response postMethod(HttpServletRequest request, HttpServletResponse response) {
        try {
            JwtPayload jwtPayload = (JwtPayload) request.getAttribute("payload");
            OrderOutput orderOutput = ((OrderService) this.service).createNewOrder(jwtPayload.getUserId());
            if (orderOutput != null) {
                return Response.success(orderOutput);
            } else {
                return new Response("error", "Chua co san pham nao trong gio hang", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("error", e.getMessage(), e);
        }
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
