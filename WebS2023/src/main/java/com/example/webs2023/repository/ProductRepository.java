package com.example.webs2023.repository;

import com.example.webs2023.base.BaseRepository;
import com.example.webs2023.entity.ProductEntity;

import java.sql.SQLException;

public class ProductRepository extends BaseRepository<ProductEntity, Long> {
    protected ProductRepository(Class<ProductEntity> entityClass) throws SQLException, ClassNotFoundException {
        super(entityClass);
    }

}
