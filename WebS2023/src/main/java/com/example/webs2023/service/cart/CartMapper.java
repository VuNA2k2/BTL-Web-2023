package com.example.webs2023.service.cart;

import com.example.webs2023.base.BaseMapper;
import com.example.webs2023.dto.cart.CartInput;
import com.example.webs2023.dto.cart.CartOutput;
import com.example.webs2023.entity.CartEntity;

public class CartMapper extends BaseMapper<CartEntity, CartInput, CartOutput> {
    public CartMapper(Class<CartEntity> entityClass, Class<CartInput> inputDtoClass, Class<CartOutput> ouputDtoClass) {
        super(entityClass, inputDtoClass, ouputDtoClass);
    }
}
