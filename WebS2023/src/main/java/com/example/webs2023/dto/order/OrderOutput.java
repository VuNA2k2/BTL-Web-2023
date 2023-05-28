package com.example.webs2023.dto.order;

import com.example.webs2023.dto.product_in_order.ProductInOrderOutput;

import java.sql.Timestamp;
import java.util.List;

public class OrderOutput {
    private Long id;
    private Long userId;
    private Timestamp orderDate;

    private String status;

    private Long totalMoney;

    private List<ProductInOrderOutput> products;

    public OrderOutput() {
    }

    public OrderOutput(Long id, Long userId, Timestamp orderDate, String status, Long totalMoney, List<ProductInOrderOutput> products) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.status = status;
        this.totalMoney = totalMoney;
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

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Long totalMoney) {
        this.totalMoney = totalMoney;
    }

    public List<ProductInOrderOutput> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInOrderOutput> products) {
        this.products = products;
    }
}
