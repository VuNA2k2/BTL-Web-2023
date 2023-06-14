package com.example.webs2023.repository;

import com.example.webs2023.base.BaseRepository;
import com.example.webs2023.entity.CategoryEntity;

import java.sql.SQLException;

public class CategoryRepository extends BaseRepository<CategoryEntity, Long> {
    public CategoryRepository(Class<CategoryEntity> entityClass) throws SQLException, ClassNotFoundException {
        super(entityClass);
    }
}
