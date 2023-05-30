package com.example.webs2023.controller;

import com.example.webs2023.base.BaseController;
import com.example.webs2023.base.DependencyInjector;
import com.example.webs2023.base.Response;
import com.example.webs2023.dto.jwt.JwtPayload;
import com.example.webs2023.dto.rate.RateInput;
import com.example.webs2023.dto.rate.RateOutput;
import com.example.webs2023.service.rate.RateService;
import com.example.webs2023.service.rate.RateServiceImpl;
import com.example.webs2023.utils.JsonFromInputConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/rates")
public class RateController extends BaseController {

    @Override
    public void init() throws ServletException {
        this.service = (RateServiceImpl) DependencyInjector.getDependency(RateService.class);
        super.init();
    }

    @Override
    protected Response getMethod(HttpServletRequest request, HttpServletResponse response) {
        try {
            String productId = request.getParameter("productId");
            if (productId == null) return new Response("error", "productId is required", null);
            Long id = Long.parseLong(productId);
            return Response.success(((RateServiceImpl) this.service).getListRateByProductId(id));
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("error", e.getMessage(), e);
        }
    }

    @Override
    protected Response<RateOutput> postMethod(HttpServletRequest request, HttpServletResponse response) {
        try {
            JwtPayload jwtPayload = (JwtPayload) request.getAttribute("payload");
            Long userId = jwtPayload.getUserId();
            RateInput rateInput = GSON.fromJson(JsonFromInputConverter.getInputStream(request.getReader()), RateInput.class);
            RateOutput rateOutput = ((RateServiceImpl) this.service).createRate(userId, rateInput);
            if (rateOutput == null) return new Response("error", "You can't rate this product", null);
            return Response.success(rateOutput);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("error", e.getMessage(), e);
        }
    }

    @Override
    protected Response<RateOutput> putMethod(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    protected Response<RateOutput> deleteMethod(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
