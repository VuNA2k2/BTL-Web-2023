package com.example.webs2023.service.cart;

import com.example.webs2023.base.BaseMapper;
import com.example.webs2023.dto.cart.CartRefProductInput;
import com.example.webs2023.dto.cart.CartRefProductOutput;
import com.example.webs2023.entity.CartRefProductEntity;

public class CartRefProductMapper extends BaseMapper<CartRefProductEntity, CartRefProductInput, CartRefProductOutput> {
    public CartRefProductMapper(Class<CartRefProductEntity> entityClass, Class<CartRefProductInput> inputDtoClass, Class<CartRefProductOutput> outputDtoClass) {
        super(entityClass, inputDtoClass, outputDtoClass);
    }
}
