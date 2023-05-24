package com.example.webs2023.dto.cart;


public class CartRefProductInput {
    private Long cartId;
    private Long productId;
    private Long quantity;

    public CartRefProductInput() {
    }

    public CartRefProductInput(Long cartId, Long productId, Long quantity) {
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}