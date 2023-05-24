package com.example.webs2023.entity;

public class CartRefProductEntity {
    private Long id;
    private Long cartId;
    private Long productId;
    private Long quantity;

    public CartRefProductEntity() {
    }

    public CartRefProductEntity(Long id, Long cartId, Long productId, Long quantity) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartRefProductEntity(Long cartId, Long productId, Long quantity) {
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
