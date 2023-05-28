package com.example.webs2023.dto.product_in_order;

public class ProductInOrderOutput {
    private Long id;
    private Long orderId;
    private Long productId;
    private Long productQuantity;
    private String productName;
    private Long productPrice;


    public ProductInOrderOutput() {
    }

    public ProductInOrderOutput(Long id, Long orderId, Long productId, Long productQuantity, String productName, Long productPrice) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.productQuantity = productQuantity;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public ProductInOrderOutput(Long orderId, Long productId, Long productQuantity, String productName, Long productPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.productQuantity = productQuantity;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getProductQuantity() {
        return productQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductQuantity(Long productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }
}
