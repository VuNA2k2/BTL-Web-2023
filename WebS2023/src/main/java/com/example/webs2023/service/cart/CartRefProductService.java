package com.example.webs2023.service.cart;

import com.example.webs2023.dto.cart.CartRefProductInput;
import com.example.webs2023.dto.cart.CartRefProductOutput;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface CartRefProductService {
    CartRefProductOutput getCartRefProductById(Long id) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
    List<CartRefProductOutput> getCartRefProductsByCartId(Long cartId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
    void addProductToCart(Long cartId, CartRefProductInput cartRefProductInput);
    CartRefProductOutput updateCartRefProduct(Long id, CartRefProductInput cartRefProductInput);
    void removeProductFromCart(Long id);
}
