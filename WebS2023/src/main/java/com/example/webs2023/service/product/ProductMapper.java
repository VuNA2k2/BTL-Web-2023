package com.example.webs2023.service.product;

import com.example.webs2023.base.BaseMapper;
import com.example.webs2023.dto.product.ProductInput;
import com.example.webs2023.dto.product.ProductOutput;
import com.example.webs2023.entity.ProductEntity;

public class ProductMapper extends BaseMapper<ProductEntity, ProductInput, ProductOutput> {
    public ProductMapper(Class<ProductEntity> entityClass, Class<ProductInput> inputDtoClass, Class<ProductOutput> ouputDtoClass) {
        super(entityClass, inputDtoClass, ouputDtoClass);
    }
}
