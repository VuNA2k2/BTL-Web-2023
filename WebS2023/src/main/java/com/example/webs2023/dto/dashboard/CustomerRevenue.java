package com.example.webs2023.dto.dashboard;

import com.example.webs2023.dto.user.UserOutput;

public class CustomerRevenue {
    private UserOutput customer;
    private Long revenue;

    public CustomerRevenue() {
    }

    public CustomerRevenue(UserOutput customer, Long revenue) {
        this.customer = customer;
        this.revenue = revenue;
    }

    public UserOutput getCustomer() {
        return customer;
    }

    public void setCustomer(UserOutput customer) {
        this.customer = customer;
    }

    public Long getRevenue() {
        return revenue;
    }

    public void setRevenue(Long revenue) {
        this.revenue = revenue;
    }
}
