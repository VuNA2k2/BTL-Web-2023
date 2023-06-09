package com.example.webs2023.controller;

import com.example.webs2023.base.BaseController;
import com.example.webs2023.base.ServiceLocator;
import com.example.webs2023.base.Response;
import com.example.webs2023.dto.jwt.JwtPayload;
import com.example.webs2023.dto.order.OrderInput;
import com.example.webs2023.dto.order.OrderOutput;
import com.example.webs2023.service.order.OrderService;
import com.example.webs2023.service.order.OrderServiceImpl;
import com.example.webs2023.utils.JsonFromInputConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/api/orders")
public class OrderController extends BaseController {


    @Override
    public void init() throws ServletException {
        this.service = (OrderServiceImpl) ServiceLocator.getDependency(OrderService.class);
        super.init();
    }

    @Override
    protected Response getMethod(HttpServletRequest request, HttpServletResponse response) {
        try {
            String status = request.getParameter("status");
            JwtPayload jwtPayload = (JwtPayload) request.getAttribute("payload");
            if (jwtPayload.getRole().equals("USER")) {
                return Response.success(((OrderService) this.service).getOrderByUserId(jwtPayload.getUserId(), status));
            } else {
                String userId = request.getParameter("userId");
                if (userId != null && !userId.isBlank()) {
                    return Response.success(((OrderService) this.service).getOrderByUserId(Long.parseLong(userId), status));
                }
                return Response.success(((OrderService) this.service).getAllListOrder(status));
            }
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
        try {
            JwtPayload jwtPayload = (JwtPayload) request.getAttribute("payload");
            String orderId = request.getParameter("orderId");
            OrderInput orderInput = GSON.fromJson(JsonFromInputConverter.getInputStream(request.getReader()), OrderInput.class);
            if (orderId != null && !orderId.isBlank()) {
                if (jwtPayload.getRole().equals("USER")) {
                    if (orderInput.getStatus().equals("CANCEL")) {
                        return Response.success(((OrderService) this.service).cancelOrder(jwtPayload.getUserId(), Long.parseLong(orderId), orderInput));
                    }
                    return new Response("error", "Khong co quyen thay doi trang thai", null);
                } else {
                    return Response.success(((OrderService) this.service).updateOrderStatus(Long.parseLong(orderId), orderInput.getStatus()));
                }
            } else {
                return new Response("error", "Khong tim thay orderId", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("error", e.getMessage(), e);
        }
    }

    @Override
    protected Response deleteMethod(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
