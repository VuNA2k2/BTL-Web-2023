package com.example.webs2023.service.dashboard;

import com.example.webs2023.base.BaseService;
import com.example.webs2023.dto.dashboard.CustomerRevenue;
import com.example.webs2023.dto.dashboard.CustomerRevenueOutput;
import com.example.webs2023.dto.dashboard.ProductRevenue;
import com.example.webs2023.dto.dashboard.ProductRevenueOutput;
import com.example.webs2023.dto.order.OrderOutput;
import com.example.webs2023.dto.product_in_order.ProductInOrderOutput;
import com.example.webs2023.dto.user.UserOutput;
import com.example.webs2023.service.order.OrderService;
import com.example.webs2023.service.product_in_order.ProductInOrderService;
import com.example.webs2023.service.user.UserService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DashboardServiceImpl implements DashboardService {

    private final ProductInOrderService productInOrderService;
    private final UserService userService;
    private final OrderService orderService;

    public DashboardServiceImpl(ProductInOrderService productInOrderService, UserService userService, OrderService orderService) {
        this.productInOrderService = productInOrderService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @Override
    public ProductRevenueOutput getRevenue() throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<ProductInOrderOutput> productInOrderOutputs = ((BaseService) productInOrderService).getAll();
        ProductRevenueOutput productRevenueOutput = new ProductRevenueOutput();
        Long totalRevenue = productInOrderOutputs.stream().mapToLong(e -> e.getProductPrice() * e.getProductQuantity()).sum();
        productRevenueOutput.setTotalRevenue(totalRevenue);
        Map<Long, List<ProductInOrderOutput>> mapGroupByProductId = productInOrderOutputs.stream().collect(Collectors.groupingBy(ProductInOrderOutput::getProductId));
        List<ProductRevenue> productRevenues = mapGroupByProductId.keySet().stream().map(e -> {
            List<ProductInOrderOutput> productInOrderOutputs1 = mapGroupByProductId.get(e);
            Long revenue = productInOrderOutputs1.stream().mapToLong(e1 -> e1.getProductPrice() * e1.getProductQuantity()).sum();
            return new ProductRevenue(e, productInOrderOutputs1.get(0).getProductName(), revenue);
        }).toList();
        productRevenueOutput.setProductRevenues(productRevenues);
        return productRevenueOutput;
    }

    @Override
    public CustomerRevenueOutput getCustomerRevenue() throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<UserOutput> userOutputs = userService.getUserByRole("USER");
        CustomerRevenueOutput customerRevenueOutput = new CustomerRevenueOutput();
        List<CustomerRevenue> customerRevenues = userOutputs.stream().map(e -> {
            CustomerRevenue customerRevenue = new CustomerRevenue();
            customerRevenue.setCustomer(e);
            try {
                List<OrderOutput> orderOutputs = orderService.getOrderByUserId(e.getId(), "DONE");
                Long totalRevenue = orderOutputs.stream().mapToLong(OrderOutput::getTotalMoney).sum();
                customerRevenue.setRevenue(totalRevenue);
                return customerRevenue;
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }).toList();
        customerRevenueOutput.setCustomerRevenues(customerRevenues);
        Long totalRevenue = customerRevenues.stream().mapToLong(CustomerRevenue::getRevenue).sum();
        customerRevenueOutput.setTotalRevenue(totalRevenue);
        return customerRevenueOutput;
    }


}
