package com.example.webs2023.service.user;

import com.example.webs2023.base.BaseMapper;
import com.example.webs2023.dto.user.UserInput;
import com.example.webs2023.dto.user.UserOutput;
import com.example.webs2023.entity.UserEntity;

public class UserMapper extends BaseMapper<UserEntity, UserInput, UserOutput> {
    public UserMapper(Class<UserEntity> entityClass, Class<UserInput> inputDtoClass, Class<UserOutput> ouputDtoClass) {
        super(entityClass, inputDtoClass, ouputDtoClass);
    }
}
