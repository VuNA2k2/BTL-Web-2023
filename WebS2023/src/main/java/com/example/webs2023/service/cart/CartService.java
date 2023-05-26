package com.example.webs2023.service.cart;

import com.example.webs2023.dto.cart.AddProductToCartRequest;
import com.example.webs2023.dto.cart.CartDetailOutput;
import com.example.webs2023.dto.cart.CartOutput;
import com.example.webs2023.entity.CartEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface CartService {
    CartDetailOutput getLeastCart(Long userId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    CartDetailOutput getDetailCartFromCartEntity(CartEntity cartEntity) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    CartDetailOutput addProductToCart(AddProductToCartRequest addProductToCartRequest, Long userId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
