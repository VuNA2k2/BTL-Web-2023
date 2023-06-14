package com.example.webs2023.entity;

import java.sql.Timestamp;

public class OrderEntity {
    private Long id;
    private Long userId;
    private Timestamp orderDate;

    private String status;

    public OrderEntity() {
    }

    public OrderEntity(Long id, Long userId, Timestamp orderDate, String status) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.status = status;
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
}
