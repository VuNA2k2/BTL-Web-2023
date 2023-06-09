package com.example.webs2023.dto.rate;

import com.example.webs2023.dto.image.ImageOutput;

import java.sql.Timestamp;
import java.util.List;

public class RateOutput {
    private Long id;
    private String comment;
    private Long star;
    private Long productId;
    private Timestamp createdAt;
    private List<ImageOutput> images;

    public RateOutput() {
    }

    public RateOutput(String comment, Long star, Long productId, Timestamp createdAt, List<ImageOutput> images) {
        this.comment = comment;
        this.star = star;
        this.productId = productId;
        this.createdAt = createdAt;
        this.images = images;
    }

    public RateOutput(Long id, String comment, Long star, Long productId, Timestamp createdAt, List<ImageOutput> images) {
        this.id = id;
        this.comment = comment;
        this.star = star;
        this.productId = productId;
        this.createdAt = createdAt;
        this.images = images;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public List<ImageOutput> getImages() {
        return images;
    }

    public void setImages(List<ImageOutput> images) {
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
