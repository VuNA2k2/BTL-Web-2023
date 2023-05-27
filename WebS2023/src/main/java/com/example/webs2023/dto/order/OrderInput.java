package com.example.webs2023.dto.order;

public class OrderInput {
    private String status;
    public OrderInput() {
    }

    public OrderInput(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
