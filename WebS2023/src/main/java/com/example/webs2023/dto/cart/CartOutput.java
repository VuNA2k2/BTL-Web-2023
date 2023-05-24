package com.example.webs2023.dto.cart;

import com.example.webs2023.dto.product.ProductOutput;

import java.util.List;

public class CartOutput {
    private Long id;
    private Long userId;

    private List<CartRefProductOutput> products;

    public List<CartRefProductOutput> getProducts() {
        return products;
    }

    public void setProducts(List<CartRefProductOutput> products) {
        this.products = products;
    }

    public CartOutput() {
    }

    public CartOutput(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
    }

    public CartOutput(Long id, Long userId, List<CartRefProductOutput> products) {
        this.id = id;
        this.userId = userId;
        this.products = products;
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
