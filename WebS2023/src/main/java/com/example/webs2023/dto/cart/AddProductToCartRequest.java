package com.example.webs2023.dto.cart;

public class AddProductToCartRequest {
    private Long productId;
    private Long quantity = 1L;

    public AddProductToCartRequest() {
    }

    public AddProductToCartRequest(Long productId, Long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getQuantity() {
        return quantity;
    }
}
