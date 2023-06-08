package com.example.webs2023.service.dashboard;

import com.example.webs2023.base.BaseService;
import com.example.webs2023.dto.dashboard.ProductRevenue;
import com.example.webs2023.dto.dashboard.RevenueOutput;
import com.example.webs2023.dto.product_in_order.ProductInOrderOutput;
import com.example.webs2023.service.product_in_order.ProductInOrderService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DashboardServiceImpl implements DashboardService {

    private final ProductInOrderService productInOrderService;

    public DashboardServiceImpl(ProductInOrderService productInOrderService) {
        this.productInOrderService = productInOrderService;
    }

    @Override
    public RevenueOutput getRevenue() throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<ProductInOrderOutput> productInOrderOutputs = ((BaseService) productInOrderService).getAll();
        RevenueOutput revenueOutput = new RevenueOutput();
        Long totalRevenue = productInOrderOutputs.stream().mapToLong(e -> e.getProductPrice() * e.getProductQuantity()).sum();
        revenueOutput.setTotalRevenue(totalRevenue);
        Map<Long, List<ProductInOrderOutput>> mapGroupByProductId = productInOrderOutputs
                .stream().collect(Collectors.groupingBy(ProductInOrderOutput::getProductId));
        List<ProductRevenue> productRevenues = mapGroupByProductId.keySet().stream().map(e -> {
            List<ProductInOrderOutput> productInOrderOutputs1 = mapGroupByProductId.get(e);
            Long revenue = productInOrderOutputs1.stream().mapToLong(e1 -> e1.getProductPrice() * e1.getProductQuantity()).sum();
            return new ProductRevenue(e, productInOrderOutputs1.get(0).getProductName(), revenue);
        }).toList();
        revenueOutput.setProductRevenues(productRevenues);
        return revenueOutput;
    }
}
