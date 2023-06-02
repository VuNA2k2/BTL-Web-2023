package com.example.webs2023.dto.dashboard;

import java.util.List;

public class RevenueOutput {
    List<ProductRevenue> productRevenues;
    private Long totalRevenue;

    public RevenueOutput() {
    }

    public RevenueOutput(Long totalRevenue, List<ProductRevenue> productRevenues) {
        this.totalRevenue = totalRevenue;
        this.productRevenues = productRevenues;
    }

    public Long getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Long totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public List<ProductRevenue> getProductRevenues() {
        return productRevenues;
    }

    public void setProductRevenues(List<ProductRevenue> productRevenues) {
        this.productRevenues = productRevenues;
    }
}
