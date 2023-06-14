package com.example.webs2023.service.dashboard;

import com.example.webs2023.dto.dashboard.CustomerRevenueOutput;
import com.example.webs2023.dto.dashboard.ProductRevenueOutput;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface DashboardService {
    ProductRevenueOutput getRevenue() throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    CustomerRevenueOutput getCustomerRevenue() throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
