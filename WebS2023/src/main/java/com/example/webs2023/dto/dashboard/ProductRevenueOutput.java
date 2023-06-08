package com.example.webs2023.dto.dashboard;

import java.util.List;

public class ProductRevenueOutput {
    List<ProductRevenue> productRevenues;
    private Long totalRevenue;

    public ProductRevenueOutput() {
    }

    public ProductRevenueOutput(Long totalRevenue, List<ProductRevenue> productRevenues) {
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
