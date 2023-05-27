package com.example.webs2023.service.product;

import com.example.webs2023.base.BaseService;
import com.example.webs2023.dto.category.CategoryOutput;
import com.example.webs2023.dto.image.ImageInput;
import com.example.webs2023.dto.image.ImageOutput;
import com.example.webs2023.dto.product.ProductInput;
import com.example.webs2023.dto.product.ProductOutput;
import com.example.webs2023.dto.product.ProductRequest;
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
        if(productEntity == null) return null;
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
        List<ProductOutput> productOutputs = productEntities.stream().map(this::getProductDetailByProductEntity).toList();
        return productOutputs;
    }

    @Override
    public List<ProductOutput> getProductByCategoryId(long categoryId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<ProductEntity> productEntities = repository.getAll("WHERE category_id = " + categoryId + " ORDER BY id DESC");
        List<ProductOutput> productOutputs = productEntities.stream().map(this::getProductDetailByProductEntity).toList();
        return productOutputs;
    }

    @Override
    public ProductOutput getProductDetailByProductEntity(ProductEntity product) {

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


    }

    @Override
    public ProductOutput createProduct(ProductRequest productRequest) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ProductEntity productEntity = mapper.getEntityFromInput(productRequest);
        ProductEntity createdProduct = repository.save(productEntity);
        ProductOutput productOutput = mapper.getOutputFromEntity(createdProduct);
        CategoryOutput categoryOutput = categoryService.getById(createdProduct.getCategoryId());
        productOutput.setCategory(categoryOutput);
        productOutput.setImages(List.of());
        if (productRequest.getImages() != null) {
            List<ImageOutput> images = productRequest.getImages().stream().map((url) -> {
                ImageInput imageInput = new ImageInput();
                imageInput.setRefId(createdProduct.getId());
                imageInput.setLink(url);
                imageInput.setRefType("PRODUCT");
                try {
                    return imageService.createImage(imageInput);
                } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                         IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }).toList();
            productOutput.setImages(images);
        }
        return productOutput;
    }

    @Override
    public ProductOutput updateProduct(long id, ProductRequest productRequest) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ProductEntity productEntity = mapper.getEntityFromInput(productRequest);
        ProductEntity updatedProduct = repository.updateById(id, productEntity);
        imageService.getImageByProductId(id).forEach((image) -> {
            try {
                if(!productRequest.getImages().contains(image.getLink())){
                    imageService.deleteImage(image.getId());
                } else {
                    productRequest.getImages().remove(image.getLink());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        if (productRequest.getImages() != null) {
            productRequest.getImages().forEach((url) -> {
                ImageInput imageInput = new ImageInput();
                imageInput.setRefId(updatedProduct.getId());
                imageInput.setLink(url);
                imageInput.setRefType("PRODUCT");
                try {
                    imageService.createImage(imageInput);
                } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                         IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return getProductDetailByProductEntity(updatedProduct);
    }

    @Override
    public void deleteProduct(long id) {
        try {
            imageService.getImageByProductId(id).forEach((image) -> {
                try {
                    imageService.deleteImage(image.getId());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
