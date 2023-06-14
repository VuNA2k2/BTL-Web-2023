package com.example.webs2023.service.product_in_order;

import com.example.webs2023.dto.cart_ref_product.CartRefProductOutput;
import com.example.webs2023.dto.product_in_order.ProductInOrderOutput;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface ProductInOrderService {
    ProductInOrderOutput createProductInOrderCartRefProduct(Long orderId, CartRefProductOutput cartRefProductOutput) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    List<ProductInOrderOutput> getProductInOrderOutputListByOrderId(Long id) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
