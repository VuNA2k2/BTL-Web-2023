package com.example.webs2023.controller;

import com.example.webs2023.base.BaseController;
import com.example.webs2023.base.DependencyInjector;
import com.example.webs2023.base.Response;
import com.example.webs2023.entity.CartEntity;
import com.example.webs2023.service.cart.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/api/carts")
public class CartController extends BaseController {

    private final CartService cartService = DependencyInjector.getDependency(CartService.class);

    @Override
    protected Response getMethod(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter("id");
            String userId = request.getParameter("userId");
            if(id != null && !id.isEmpty()) {
                return  Response.success(cartService.getCartById(Long.parseLong(id)));
            } else if(userId != null &&  !userId.isEmpty()) {
                return Response.success(cartService.getCartByUserId(Long.parseLong(userId)));
            }
            return new Response("fail", "Looix", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("fail", "Looix", null);
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
