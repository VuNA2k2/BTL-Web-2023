package com.example.webs2023.dto.product;

public class ProductInput {
    private String name;
    private String description;
    private Long price;
    private Long categoryId;

    public ProductInput() {
    }

    public ProductInput(String name, String description, Long price, Long categoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
