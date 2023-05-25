package com.example.webs2023.entity;

public class CartEntity {
    private Long id;
    private Long userId;

    public CartEntity() {
    }

    public CartEntity(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
    }

    public CartEntity(Long userId) {
        this.userId = userId;
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
}
