package com.example.webs2023.dto.category;

public class CategoryInput {
    private String name;
    private String description;
    public CategoryInput() {
    }
    public CategoryInput(String name, String description) {
        this.name = name;
        this.description = description;
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
}
