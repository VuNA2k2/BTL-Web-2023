package com.example.webs2023.service.order;

import com.example.webs2023.base.BaseMapper;
import com.example.webs2023.dto.order.OrderInput;
import com.example.webs2023.dto.order.OrderOutput;
import com.example.webs2023.entity.OrderEntity;

public class OrderMapper extends BaseMapper<OrderEntity, OrderInput, OrderOutput> {
    public OrderMapper(Class<OrderEntity> entityClass, Class<OrderInput> inputDtoClass, Class<OrderOutput> ouputDtoClass) {
        super(entityClass, inputDtoClass, ouputDtoClass);
    }
}
