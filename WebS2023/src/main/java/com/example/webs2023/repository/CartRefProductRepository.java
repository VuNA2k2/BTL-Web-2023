package com.example.webs2023.repository;

import com.example.webs2023.base.BaseRepository;
import com.example.webs2023.entity.CartsRefProductEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CartRefProductRepository extends BaseRepository<CartsRefProductEntity, Long> {
    public CartRefProductRepository(Class<CartsRefProductEntity> entityClass) throws SQLException, ClassNotFoundException {
        super(entityClass);
    }

    public List<CartsRefProductEntity> getCartsRefProductByCartId(Long cartId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return rawQuery("SELECT * FROM carts_ref_products WHERE cart_id = " + cartId);
    }

    public Long existsByCartIdAndProductId(Long cartId, Long productId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return rawQuery("SELECT * FROM carts_ref_products WHERE cart_id = " + cartId + " AND product_id = " + productId).get(0).getId();
    }

    public void deleteByCartId(Long cartId) throws SQLException {
        String sql = "DELETE FROM carts_ref_products WHERE cart_id = " + cartId;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
