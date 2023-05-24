package com.example.webs2023.dto.cart;

public class CartOutput {
    private Long id;
    private Long userId;
    private double totalMoney;

    public CartOutput() {
    }

    public CartOutput(Long id, Long userId, double totalMoney) {
        this.id = id;
        this.userId = userId;
        this.totalMoney = totalMoney;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }
}
