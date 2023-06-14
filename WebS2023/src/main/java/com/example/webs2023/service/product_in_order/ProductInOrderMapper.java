package com.example.webs2023.service.product_in_order;

import com.example.webs2023.base.BaseMapper;
import com.example.webs2023.dto.product_in_order.ProductInOrderInput;
import com.example.webs2023.dto.product_in_order.ProductInOrderOutput;
import com.example.webs2023.entity.ProductsInOrderEntity;

public class ProductInOrderMapper extends BaseMapper<ProductsInOrderEntity, ProductInOrderInput, ProductInOrderOutput> {

    public ProductInOrderMapper(Class<ProductsInOrderEntity> entityClass, Class<ProductInOrderInput> inputDtoClass, Class<ProductInOrderOutput> ouputDtoClass) {
        super(entityClass, inputDtoClass, ouputDtoClass);
    }
}
