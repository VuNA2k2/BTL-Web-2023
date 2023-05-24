package com.example.webs2023.repository;

import com.example.webs2023.base.BaseRepository;
import com.example.webs2023.entity.CartEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class CartRepository extends BaseRepository<CartEntity, Long> {

    public CartRepository(Class<CartEntity> entityClass) throws SQLException, ClassNotFoundException {
        super(entityClass);
    }

    public List<CartEntity> getCartByUserId(Long userId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return rawQuery("SELECT * FROM " + tableName + " WHERE user_id = " + userId);
    }
}
