package com.example.webs2023.dto.cart;

public class CartInput {

    private Long userId;
    private double totalMoney;

    public CartInput() {
    }

    public CartInput(Long userId, double totalMoney) {

        this.userId = userId;
        this.totalMoney = totalMoney;
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
