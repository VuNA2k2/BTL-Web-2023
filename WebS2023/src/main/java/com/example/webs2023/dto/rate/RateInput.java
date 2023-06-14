package com.example.webs2023.dto.rate;

import java.util.List;

public class RateInput {
    private String comment;
    private Long star;
    private Long productInOrderId;
    private List<String> images;

    public RateInput() {
    }

    public RateInput(String comment, Long star, Long productInOrderId, List<String> images) {
        this.comment = comment;
        this.star = star;
        this.productInOrderId = productInOrderId;
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

    public Long getProductInOrderId() {
        return productInOrderId;
    }

    public void setProductInOrderId(Long productInOrderId) {
        this.productInOrderId = productInOrderId;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
