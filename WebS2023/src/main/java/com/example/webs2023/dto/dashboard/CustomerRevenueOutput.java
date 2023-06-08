package com.example.webs2023.dto.dashboard;

import com.example.webs2023.dto.user.UserOutput;

import java.util.List;

public class CustomerRevenueOutput {
    private Long totalRevenue;
    private List<CustomerRevenue> customerRevenues;

    public CustomerRevenueOutput() {
    }

    public CustomerRevenueOutput(Long totalRevenue, List<CustomerRevenue> customerRevenues) {
        this.totalRevenue = totalRevenue;
        this.customerRevenues = customerRevenues;
    }

    public Long getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Long totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public List<CustomerRevenue> getCustomerRevenues() {
        return customerRevenues;
    }

    public void setCustomerRevenues(List<CustomerRevenue> customerRevenues) {
        this.customerRevenues = customerRevenues;
    }
}
