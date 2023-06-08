package com.example.webs2023.controller;

import com.example.webs2023.base.BaseController;
import com.example.webs2023.base.Response;
import com.example.webs2023.base.ServiceLocator;
import com.example.webs2023.service.dashboard.DashboardService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/dashboard")
public class DashboardController extends BaseController {
    private final DashboardService dashboardService = ServiceLocator.getDependency(DashboardService.class);

    @Override
    protected Response getMethod(HttpServletRequest request, HttpServletResponse response) {
        try {
            String action = request.getParameter("action");
            if (action == null) {
                return new Response("error", "action is required", null);
            }
            return switch (action) {
                case "PRODUCT_REVENUE" -> Response.success(dashboardService.getRevenue());
                case "CUSTOMER_REVENUE" -> Response.success(dashboardService.getCustomerRevenue());
                default -> new Response("error", "action is invalid", null);
            };
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("error", e.getMessage(), e);
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
