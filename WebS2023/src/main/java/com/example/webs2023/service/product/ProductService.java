package com.example.webs2023.service.product;

import com.example.webs2023.dto.product.ProductOutput;
import com.example.webs2023.dto.product.ProductRequest;
import com.example.webs2023.entity.ProductEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface ProductService {

    ProductOutput getProductById(Long id) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    List<ProductOutput> getAllProduct() throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    List<ProductOutput> getProductByCategoryId(long categoryId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    ProductOutput getProductDetailByProductEntity(ProductEntity product);

    ProductOutput createProduct(ProductRequest productRequest) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
