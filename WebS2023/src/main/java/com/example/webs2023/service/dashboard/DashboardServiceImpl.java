package com.example.webs2023.service.dashboard;

import com.example.webs2023.base.BaseService;
import com.example.webs2023.dto.dashboard.ProductRevenue;
import com.example.webs2023.dto.dashboard.RevenueOutput;
import com.example.webs2023.dto.product_in_order.ProductInOrderOutput;
import com.example.webs2023.service.product_in_order.ProductInOrderService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class DashboardServiceImpl implements DashboardService {

    private final ProductInOrderService productInOrderService;

    public DashboardServiceImpl(ProductInOrderService productInOrderService) {
        this.productInOrderService = productInOrderService;
    }

    @Override
    public RevenueOutput getRevenue() throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<ProductInOrderOutput> productInOrderOutputs = ((BaseService) productInOrderService).getAll();
        RevenueOutput revenueOutput = new RevenueOutput();
        Long totalRevenue = productInOrderOutputs.stream().mapToLong(e -> {
            return e.getProductPrice() * e.getProductQuantity();
        }).sum();
        revenueOutput.setTotalRevenue(totalRevenue);
        List<ProductRevenue> productRevenues = productInOrderOutputs.stream().map(e -> new ProductRevenue(e.getProductId(), e.getProductName(), e.getProductPrice() * e.getProductQuantity())).toList();
        revenueOutput.setProductRevenues(productRevenues);
        return revenueOutput;
    }
}
