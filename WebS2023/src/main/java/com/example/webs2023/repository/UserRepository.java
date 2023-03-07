package com.example.webs2023.repository;

import com.example.webs2023.base.BaseRepository;
import com.example.webs2023.entity.UserEntity;

import java.sql.SQLException;

public class UserRepository extends BaseRepository<UserEntity, Long> {

    public UserRepository(Class<UserEntity> entityClass) throws SQLException, ClassNotFoundException {
        super(entityClass);
    }
}
