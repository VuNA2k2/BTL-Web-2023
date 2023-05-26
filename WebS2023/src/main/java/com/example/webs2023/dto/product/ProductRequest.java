package com.example.webs2023.dto.product;

import java.util.List;

public class ProductRequest extends ProductInput {

    List<String> images;

    public ProductRequest() {
    }

    public ProductRequest(java.lang.String name, java.lang.String description, Long price, Long categoryId, List<String> images) {
        super(name, description, price, categoryId);
        this.images = images;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
