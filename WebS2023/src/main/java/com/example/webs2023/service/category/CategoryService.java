package com.example.webs2023.service.category;

import com.example.webs2023.base.BaseService;
import com.example.webs2023.dto.category.CategoryInput;
import com.example.webs2023.dto.category.CategoryOutput;
import com.example.webs2023.entity.CategoryEntity;
import com.example.webs2023.repository.CategoryRepository;

public class CategoryService extends BaseService<CategoryEntity, Long, CategoryInput, CategoryOutput> {
    public CategoryService(CategoryRepository repository) {
        super();
        this.repository = repository;
        this.mapper = new CategoryMapper(CategoryEntity.class, CategoryInput.class, CategoryOutput.class);
    }
}
