package com.example.webs2023.entity;

public class CartEntity {
    private Long id;
    private Long userId;

    private double totalMoney;

    public CartEntity() {
    }

    public CartEntity(Long id, Long userId, double totalMoney) {
        this.id = id;
        this.userId = userId;
        this.totalMoney = totalMoney;
    }
    public CartEntity(Long userId, double totalMoney) {
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
