package com.example.webs2023.service.product;

import com.example.webs2023.base.BaseService;
import com.example.webs2023.dto.product.ProductInputDto;
import com.example.webs2023.dto.product.ProductOutputDto;
import com.example.webs2023.entity.ProductEntity;
import com.example.webs2023.repository.ProductRepository;

public class ProductService extends BaseService<ProductEntity, Long, ProductInputDto, ProductOutputDto> {
    public ProductService(ProductRepository repository) {
        super();
        this.repository = repository;
        this.mapper = new ProductMapper(ProductEntity.class, ProductInputDto.class, ProductOutputDto.class);
    }

}
