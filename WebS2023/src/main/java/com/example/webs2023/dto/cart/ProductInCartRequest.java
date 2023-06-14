package com.example.webs2023.dto.cart;

public class ProductInCartRequest {
    private Long productId;
    private Long quantity = 1L;

    public ProductInCartRequest() {
    }

    public ProductInCartRequest(Long productId, Long quantity) {
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
