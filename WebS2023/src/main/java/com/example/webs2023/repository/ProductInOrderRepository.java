package com.example.webs2023.repository;

import com.example.webs2023.base.BaseRepository;
import com.example.webs2023.entity.ProductsInOrderEntity;

import java.sql.SQLException;

public class ProductInOrderRepository extends BaseRepository<ProductsInOrderEntity, Long> {
    public ProductInOrderRepository(Class<ProductsInOrderEntity> entityClass) throws SQLException, ClassNotFoundException {
        super(entityClass);
    }
}
