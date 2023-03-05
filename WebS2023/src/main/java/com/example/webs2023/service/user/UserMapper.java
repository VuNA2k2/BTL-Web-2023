package com.example.webs2023.service.user;

import com.example.webs2023.dto.user.UserInput;
import com.example.webs2023.entity.UserEntity;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserMapper {

    public static void main(String[] args) {
        new UserMapper().getEntityFromInput(new UserInput("abc", "123"));
    }
    public UserEntity getEntityFromInput(UserInput userInput) {
        if(userInput == null) return null;
        UserEntity userEntity = new UserEntity();
        var fields = Arrays.stream(UserInput.class.getDeclaredFields())
                .collect(Collectors.toMap(Field::getName, Function.identity()));
        var fieldE = Arrays.stream(UserInput.class.getDeclaredFields()).collect(Collectors.toMap(Field::getName, Function.identity()));
        fields.forEach((name, field) -> {
            Field entityField = fieldE.get(name);
            if (entityField == null) return;
            try {
                var descriptor = new PropertyDescriptor(name, UserInput.class);
                Method readMethod = descriptor.getReadMethod();
                Object data = readMethod.invoke(userInput);
                var descriptor2 = new PropertyDescriptor(name, UserEntity.class);
                Method setMethod = descriptor2.getWriteMethod();
                setMethod.invoke(userEntity, data);
            } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
                e.printStackTrace();
            }
        });
        return userEntity;
    }
}
