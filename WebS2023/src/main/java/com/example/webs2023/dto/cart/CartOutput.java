package com.example.webs2023.dto.cart;

public class CartOutput {
    private Long id;
    private double totalmoneny;

    public CartOutput() {
    }

    public CartOutput(Long id, double totalmoneny) {
        this.id = id;
        this.totalmoneny = totalmoneny;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalmoneny() {
        return totalmoneny;
    }

    public void setTotalmoneny(double totalmoneny) {
        this.totalmoneny = totalmoneny;
    }
}