package com.example.webs2023.base;

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

public class BaseMapper<E, I, O> {
    private final Class<E> entityClass;
    private final Class<I> inputDtoClass;
    private final Class<O> ouputDtoClass;

    public BaseMapper(Class<E> entityClass, Class<I> inputDtoClass, Class<O> ouputDtoClass) {
        this.entityClass = entityClass;
        this.inputDtoClass = inputDtoClass;
        this.ouputDtoClass = ouputDtoClass;
    }

    public <E,I> E getEntityFromInput(I inputDto) {
        if(inputDto == null) return null;
        try {
            E entity = (E) entityClass.getDeclaredConstructor().newInstance();
            var entityFields = Arrays.stream(entityClass.getDeclaredFields()).collect(Collectors.toMap(Field::getName, Function.identity()));
            var inputDtoFields = Arrays.stream(inputDtoClass.getDeclaredFields()).collect(Collectors.toMap(Field::getName, Function.identity()));
            inputDtoFields.forEach((name, field) -> {
                Field entityField = entityFields.get(name);
                if(entityField == null) return;
                try {
                    PropertyDescriptor inputDescriptor = new PropertyDescriptor(name, inputDtoClass);
                    Method readMethod = inputDescriptor.getReadMethod();
                    Object data = readMethod.invoke(inputDto);
                    PropertyDescriptor entityDescriptor = new PropertyDescriptor(name, entityClass);
                    Method setMethod = entityDescriptor.getWriteMethod();
                    setMethod.invoke(entity, data);
                } catch (IntrospectionException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
            return entity;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <O, E> O getOutputFromEntity(E entity) {
        if(entity == null) return null;
        try {
            O output = (O) ouputDtoClass.getDeclaredConstructor().newInstance();
            var entityFields = Arrays.stream(entityClass.getDeclaredFields()).collect(Collectors.toMap(Field::getName, Function.identity()));
            var outputDtoFields = Arrays.stream(ouputDtoClass.getDeclaredFields()).collect(Collectors.toMap(Field::getName, Function.identity()));
            entityFields.forEach((name, field) -> {
                Field outputField = outputDtoFields.get(name);
                if(outputField == null) return;

                try {
                    PropertyDescriptor entityDescriptor = new PropertyDescriptor(name, entityClass);
                    Method readMethod = entityDescriptor.getReadMethod();
                    Object data = readMethod.invoke(entity);
                    PropertyDescriptor outputDescriptor = new PropertyDescriptor(name, ouputDtoClass);
                    Method setMethod = outputDescriptor.getWriteMethod();
                    setMethod.invoke(output, data);
                } catch (IntrospectionException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
            return output;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

}
