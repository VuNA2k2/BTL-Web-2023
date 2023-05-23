package com.example.webs2023.dto.cart;

public class CartInput {
    private double totalmoney;

    public CartInput() {
    }

    public CartInput(double totalmoney) {
        this.totalmoney = totalmoney;
    }

    public double getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(double totalmoney) {
        this.totalmoney = totalmoney;
    }
}
