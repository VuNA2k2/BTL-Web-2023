package com.example.webs2023.controller;

import com.example.webs2023.base.BaseController;
import com.example.webs2023.base.DependencyInjector;
import com.example.webs2023.base.Response;
import com.example.webs2023.dto.cart.AddProductToCartRequest;
import com.example.webs2023.dto.cart.CartDetailOutput;
import com.example.webs2023.dto.jwt.JwtPayload;
import com.example.webs2023.dto.user.UserOutput;
import com.example.webs2023.service.cart.CartService;
import com.example.webs2023.service.cart.CartServiceImpl;
import com.example.webs2023.service.jwt.JwtService;
import com.example.webs2023.service.user.UserService;
import com.example.webs2023.utils.JsonFromInputConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/carts")
public class CartController extends BaseController {
    private final JwtService jwtService = DependencyInjector.getDependency(JwtService.class);
    private final UserService userService = DependencyInjector.getDependency(UserService.class);
    ;

    @Override
    public void init() throws ServletException {
        this.service = (CartServiceImpl) DependencyInjector.getDependency(CartService.class);
        super.init();
    }

    @Override
    protected Response getMethod(HttpServletRequest request, HttpServletResponse response) {
        try {
            String token = request.getHeader("Authorization").substring(7);
            JwtPayload jwtPayload = jwtService.getPayload(token);
            UserOutput userOutput = userService.getUserById(jwtPayload.getUserId());
            return Response.success((CartDetailOutput) ((CartService) service).getLeastCart(userOutput.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("error", e.getMessage(), null);
        }
    }

    @Override
    protected Response postMethod(HttpServletRequest request, HttpServletResponse response) {
        try {
            JwtPayload jwtPayload = (JwtPayload) request.getAttribute("payload");
            AddProductToCartRequest addProductToCartRequest = GSON.fromJson(JsonFromInputConverter.getInputStream(request.getReader()), AddProductToCartRequest.class);
            return Response.success(((CartService) service).addProductToCart(addProductToCartRequest, jwtPayload.getUserId()));
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
