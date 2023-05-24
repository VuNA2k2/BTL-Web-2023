package com.example.webs2023.dto.cart;

public class CartInput {

    private Long userId;

    public CartInput() {
    }

    public CartInput(Long userId) {

        this.userId = userId;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}