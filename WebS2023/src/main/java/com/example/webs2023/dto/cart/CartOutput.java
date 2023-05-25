package com.example.webs2023.dto.cart;

public class CartOutput {
    private Long id;
    private Long userId;

    public CartOutput() {
    }

    public CartOutput(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
    }

    public CartOutput(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
