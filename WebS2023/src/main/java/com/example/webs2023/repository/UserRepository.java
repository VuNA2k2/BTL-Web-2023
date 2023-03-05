package com.example.webs2023.repository;

import com.example.webs2023.entity.UserEntity;

public interface UserRepository {
    public UserEntity save(UserEntity userEntity);
    public UserEntity getUserById();
}
