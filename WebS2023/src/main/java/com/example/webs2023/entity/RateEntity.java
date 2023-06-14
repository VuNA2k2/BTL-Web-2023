package com.example.webs2023.entity;

import java.sql.Timestamp;

public class RateEntity {
    private Long id;
    private Long userId;
    private String comment;
    private Long star;
    private Timestamp createdAt;
    private Long productId;

    public RateEntity() {
    }

    public RateEntity(Long id, Long userId, String comment, Long star, Timestamp createdAt, Long productId) {
        this.id = id;
        this.userId = userId;
        this.comment = comment;
        this.star = star;
        this.createdAt = createdAt;
        this.productId = productId;
    }

    public RateEntity(Long userId, String comment, Long star, Timestamp createdAt, Long productId) {
        this.userId = userId;
        this.comment = comment;
        this.star = star;
        this.createdAt = createdAt;
        this.productId = productId;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getStar() {
        return star;
    }

    public void setStar(Long star) {
        this.star = star;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
