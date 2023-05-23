package com.example.webs2023.entity;

public class CartEntity {
    private Long id;
    private double totalmoney;

    public CartEntity() {
    }

    public CartEntity(Long id, double totalmoney) {
        this.id = id;
        this.totalmoney = totalmoney;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(double totalmoney) {
        this.totalmoney = totalmoney;
    }
}
