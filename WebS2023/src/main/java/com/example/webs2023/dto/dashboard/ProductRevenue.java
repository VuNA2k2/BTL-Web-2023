package com.example.webs2023.dto.dashboard;

public class ProductRevenue {
    private Long id;
    private String name;
    private Long revenue;

    public ProductRevenue() {
    }

    public ProductRevenue(String name, Long revenue) {
        this.name = name;
        this.revenue = revenue;
    }

    public ProductRevenue(Long id, String name, Long revenue) {
        this.id = id;
        this.name = name;
        this.revenue = revenue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRevenue() {
        return revenue;
    }

    public void setRevenue(Long revenue) {
        this.revenue = revenue;
    }
}
