package com.example.webs2023.service.product_in_order;

import com.example.webs2023.base.BaseService;
import com.example.webs2023.dto.cart_ref_product.CartRefProductOutput;
import com.example.webs2023.dto.product_in_order.ProductInOrderInput;
import com.example.webs2023.dto.product_in_order.ProductInOrderOutput;
import com.example.webs2023.entity.ProductsInOrderEntity;
import com.example.webs2023.repository.ProductInOrderRepository;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class ProductInOrderServiceImpl extends BaseService<ProductsInOrderEntity, Long, ProductInOrderInput, ProductInOrderOutput> implements ProductInOrderService {
    public ProductInOrderServiceImpl(ProductInOrderRepository repository) {
        this.repository = repository;
        this.mapper = new ProductInOrderMapper(ProductsInOrderEntity.class, ProductInOrderInput.class, ProductInOrderOutput.class);
    }


    @Override
    public ProductInOrderOutput createProductInOrderCartRefProduct(Long orderId, CartRefProductOutput cartRefProductOutput) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ProductsInOrderEntity productsInOrderEntity = new ProductsInOrderEntity();
        productsInOrderEntity.setOrderId(orderId);
        productsInOrderEntity.setProductId(cartRefProductOutput.getProductId());
        productsInOrderEntity.setProductQuantity(cartRefProductOutput.getQuantity());
        productsInOrderEntity.setProductPrice(cartRefProductOutput.getProduct().getPrice());
        productsInOrderEntity.setProductName(cartRefProductOutput.getProduct().getName());
        return mapper.getOutputFromEntity(repository.save(productsInOrderEntity));
    }

    @Override
    public List<ProductInOrderOutput> getProductInOrderOutputListByOrderId(Long id) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<ProductsInOrderEntity> productsInOrderEntities = repository.getAll("WHERE order_id = " + id + " ORDER BY id DESC");
        return productsInOrderEntities.stream().map(productsInOrderEntity -> {
            try {
                return (ProductInOrderOutput) mapper.getOutputFromEntity(productsInOrderEntity);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }
}
