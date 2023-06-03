package com.example.webs2023.service.dashboard;

import com.example.webs2023.dto.dashboard.RevenueOutput;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface DashboardService {
    RevenueOutput getRevenue() throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
