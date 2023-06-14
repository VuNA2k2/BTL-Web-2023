package com.example.webs2023.service.cart_ref_product;

import com.example.webs2023.base.BaseMapper;
import com.example.webs2023.dto.cart_ref_product.CartRefProductInput;
import com.example.webs2023.dto.cart_ref_product.CartRefProductOutput;
import com.example.webs2023.entity.CartsRefProductEntity;

public class CartRefProductMapper extends BaseMapper<CartsRefProductEntity, CartRefProductInput, CartRefProductOutput> {
    public CartRefProductMapper(Class<CartsRefProductEntity> entityClass, Class<CartRefProductInput> inputDtoClass, Class<CartRefProductOutput> ouputDtoClass) {
        super(entityClass, inputDtoClass, ouputDtoClass);
    }
}
