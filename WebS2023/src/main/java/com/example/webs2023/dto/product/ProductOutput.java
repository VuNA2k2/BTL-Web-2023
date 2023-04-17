package com.example.webs2023.dto.product;

import com.example.webs2023.dto.category.CategoryOutput;

public class ProductOutput {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private CategoryOutput category;
    public ProductOutput() {
    }
    public ProductOutput(Long id, String name, String description, Long price, CategoryOutput category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public CategoryOutput getCategory() {
        return category;
    }

    public void setCategory(CategoryOutput category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
