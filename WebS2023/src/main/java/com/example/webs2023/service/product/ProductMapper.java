package com.example.webs2023.service.product;

import com.example.webs2023.base.BaseMapper;
import com.example.webs2023.dto.product.ProductInputDto;
import com.example.webs2023.dto.product.ProductOutputDto;
import com.example.webs2023.entity.ProductEntity;

public class ProductMapper extends BaseMapper<ProductEntity, ProductInputDto, ProductOutputDto> {
    public ProductMapper(Class<ProductEntity> entityClass, Class<ProductInputDto> inputDtoClass, Class<ProductOutputDto> ouputDtoClass) {
        super(entityClass, inputDtoClass, ouputDtoClass);
    }
}
