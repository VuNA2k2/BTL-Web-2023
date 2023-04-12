package com.example.webs2023.dto.product;

public class ProductInputDto {
    private String name;
    private String description;
    private Long price;

    public ProductInputDto() {
    }

    public ProductInputDto(String name, String description, Long price) {
        this.name = name;
        this.description = description;
        this.price = price;
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
