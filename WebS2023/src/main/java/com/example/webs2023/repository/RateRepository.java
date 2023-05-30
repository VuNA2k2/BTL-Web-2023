package com.example.webs2023.repository;

import com.example.webs2023.base.BaseRepository;
import com.example.webs2023.entity.RateEntity;

import java.sql.SQLException;

public class RateRepository extends BaseRepository<RateEntity, Long> {
    public RateRepository(Class<RateEntity> entityClass) throws SQLException, ClassNotFoundException {
        super(entityClass);
    }
}
