package com.example.webs2023.service.user;

import com.example.webs2023.base.BaseMapper;
import com.example.webs2023.dto.user.UserInput;
import com.example.webs2023.dto.user.UserOutput;
import com.example.webs2023.entity.UserEntity;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserMapper extends BaseMapper<UserEntity, UserInput, UserOutput> {


    public UserMapper(Class<UserEntity> entityClass, Class<UserInput> inputDtoClass, Class<UserOutput> ouputDtoClass) {
        super(entityClass, inputDtoClass, ouputDtoClass);
    }
}
