package com.example.webs2023.repository;

import com.example.webs2023.base.BaseRepository;
import com.example.webs2023.entity.ProductsInOrderEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductInOrderRepository extends BaseRepository<ProductsInOrderEntity, Long> {
    public ProductInOrderRepository(Class<ProductsInOrderEntity> entityClass) throws SQLException, ClassNotFoundException {
        super(entityClass);
    }

    public Boolean existsByUserOrderAndId(Long userOrderId, Long id) throws SQLException {
        String sql = "SELECT COUNT(*) > 0 FROM products_in_orders JOIN orders ON orders.id = products_in_orders.order_id WHERE orders.user_id = ? AND products_in_orders.id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, userOrderId);
        statement.setLong(2, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            return resultSet.getBoolean(1);
        }
        return false;
    }
}
