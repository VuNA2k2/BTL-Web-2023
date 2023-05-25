package com.example.webs2023.service.product;

import com.example.webs2023.base.BaseService;
import com.example.webs2023.dto.category.CategoryOutput;
import com.example.webs2023.dto.image.ImageOutput;
import com.example.webs2023.dto.product.ProductInput;
import com.example.webs2023.dto.product.ProductOutput;
import com.example.webs2023.entity.ProductEntity;
import com.example.webs2023.repository.ProductRepository;
import com.example.webs2023.service.category.CategoryService;
import com.example.webs2023.service.image.ImageService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl extends BaseService<ProductEntity, Long, ProductInput, ProductOutput> implements ProductService {
    private final CategoryService categoryService;
    private final ImageService imageService;

    public ProductServiceImpl(ProductRepository repository, CategoryService categoryService, ImageService imageService) {
        this.imageService = imageService;
        this.categoryService = categoryService;
        this.repository = repository;
        this.mapper = new ProductMapper(ProductEntity.class, ProductInput.class, ProductOutput.class);
    }

    @Override
    public ProductOutput getProductById(Long id) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ProductEntity productEntity = repository.getById(id);
        ProductOutput productOutput = mapper.getOutputFromEntity(productEntity);
        CategoryOutput categoryOutput = categoryService.getById(productEntity.getCategoryId());
        List<ImageOutput> imageOutputs = imageService.getImageByProductId(productEntity.getId());
        productOutput.setImages(imageOutputs);
        productOutput.setCategory(categoryOutput);
        return productOutput;
    }

    @Override
    public List<ProductOutput> getAllProduct() throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<ProductEntity> productEntities = repository.getAll();
        List<ProductOutput> productOutputs = productEntities.stream().map((product) -> {
            CategoryOutput categoryOutput;
            try {
                categoryOutput = categoryService.getById(product.getCategoryId());
                List<ImageOutput> imageOutputs = imageService.getImageByProductId(product.getId());
                ProductOutput productOutput = mapper.getOutputFromEntity(product);
                productOutput.setCategory(categoryOutput);
                productOutput.setImages(imageOutputs);
                return productOutput;
            } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }).toList();
        return productOutputs;
    }


}
