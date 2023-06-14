package com.example.webs2023.repository;

import com.example.webs2023.base.BaseRepository;
import com.example.webs2023.entity.OrderEntity;

import java.sql.SQLException;

public class OrderRepository extends BaseRepository<OrderEntity, Long> {
    public OrderRepository(Class<OrderEntity> entityClass) throws SQLException, ClassNotFoundException {
        super(entityClass);
    }
}
