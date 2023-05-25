package com.example.webs2023.service.product;

import com.example.webs2023.dto.product.ProductOutput;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface ProductService {

    ProductOutput getProductById(Long id) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
