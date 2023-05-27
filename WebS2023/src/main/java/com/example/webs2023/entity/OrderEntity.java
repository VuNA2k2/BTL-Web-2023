package com.example.webs2023.entity;

import java.sql.Time;

public class OrderEntity {
    private Long id;
    private Long userId;
    private Time orderDate;

    private String status;

    public OrderEntity() {
    }

    public OrderEntity(Long id, Long userId, Time orderDate, String status) {
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

    public Time getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Time orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
