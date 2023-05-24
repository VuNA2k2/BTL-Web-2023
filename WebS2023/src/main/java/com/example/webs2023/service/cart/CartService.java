package com.example.webs2023.service.cart;

import com.example.webs2023.dto.cart.CartInput;
import com.example.webs2023.dto.cart.CartOutput;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface CartService {
    CartOutput getCartById(Long id);
    CartOutput getCartByUserId(Long userId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
    CartOutput saveCart(CartInput cartInput);
    CartOutput updateCart(Long id, CartInput cartInput);
    void deleteCart(Long id);
}
