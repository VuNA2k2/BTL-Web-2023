package com.example.webs2023.service.cart_ref_product;

import com.example.webs2023.dto.cart_ref_product.CartRefProductOutput;
import com.example.webs2023.entity.CartsRefProductEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface CartRefProductService {
    List<CartRefProductOutput> getCartsRefProductByCartId(Long cartId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    CartRefProductOutput getCartRefProductOutputByCartRefProductEntity(CartsRefProductEntity cartsRefProductEntity);
}
