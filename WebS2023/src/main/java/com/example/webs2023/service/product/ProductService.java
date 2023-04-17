package com.example.webs2023.service.product;

import com.example.webs2023.base.BaseService;
import com.example.webs2023.base.DependencyInjector;
import com.example.webs2023.dto.category.CategoryOutput;
import com.example.webs2023.dto.product.ProductInput;
import com.example.webs2023.dto.product.ProductOutput;
import com.example.webs2023.entity.ProductEntity;
import com.example.webs2023.repository.ProductRepository;
import com.example.webs2023.service.category.CategoryService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class ProductService extends BaseService<ProductEntity, Long, ProductInput, ProductOutput> {
    private final CategoryService categoryService = DependencyInjector.getDependency(CategoryService.class);
    public ProductService(ProductRepository repository) {
        super();
        this.repository = repository;
        this.mapper = new ProductMapper(ProductEntity.class, ProductInput.class, ProductOutput.class);
    }

    @Override
    public ProductOutput getById(Long id) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ProductEntity productEntity = repository.getById(id);
        ProductOutput productOutput = mapper.getOutputFromEntity(productEntity);
        CategoryOutput categoryOutput = categoryService.getById(productEntity.getCategoryId());
        productOutput.setCategory(categoryOutput);
        return productOutput;
    }
}
