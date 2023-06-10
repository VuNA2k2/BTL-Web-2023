package com.example.webs2023.service.category;

import com.example.webs2023.base.BaseService;
import com.example.webs2023.dto.category.CategoryInput;
import com.example.webs2023.dto.category.CategoryOutput;
import com.example.webs2023.entity.CategoryEntity;
import com.example.webs2023.repository.CategoryRepository;
import com.example.webs2023.repository.ProductRepository;

import java.sql.SQLException;

public class CategoryService extends BaseService<CategoryEntity, Long, CategoryInput, CategoryOutput> {
    private final ProductRepository productRepository;
    public CategoryService(CategoryRepository repository, ProductRepository productRepository) {
        super();
        this.repository = repository;
        this.productRepository = productRepository;
        this.mapper = new CategoryMapper(CategoryEntity.class, CategoryInput.class, CategoryOutput.class);
    }

    @Override
    public boolean deleteById(Long id) throws SQLException {
        productRepository.deleteByCategoryId(id);
        return super.deleteById(id);
    }
}
