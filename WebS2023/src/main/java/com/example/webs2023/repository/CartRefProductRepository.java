package com.example.webs2023.repository;

import com.example.webs2023.base.BaseRepository;
import com.example.webs2023.entity.CartRefProductEntity;
import com.example.webs2023.config.DatabaseConnection;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CartRefProductRepository extends BaseRepository<CartRefProductEntity, Long> {

    public CartRefProductRepository(Class<CartRefProductEntity> entityClass) throws SQLException, ClassNotFoundException {
        super(entityClass);
    }

    public List<CartRefProductEntity> getCartRefProductsByCartId(Long cartId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return rawQuery("SELECT * FROM " + tableName + " WHERE cartId = " + cartId);
    }

    public void deleteByCartId(Long cartId) throws SQLException {

        try {

            String query = "DELETE FROM " + tableName + " WHERE cartId = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement = connection.prepareStatement(query);
            statement.setLong(1, cartId);
            statement.executeUpdate();
        } catch (Exception e) {

        }


    }

//    public void addQuantityToCartRefProduct(Long cartRefProductId, int quantity) throws SQLException {
//
//
//        try {
//
//            String query = "UPDATE " + tableName + " SET quantity = quantity + ? WHERE id = ?";
//            PreparedStatement statement = this.connection.prepareStatement(query);
//            statement = connection.prepareStatement(query);
//            statement.setInt(1, quantity);
//            statement.setLong(2, cartRefProductId);
//            statement.executeUpdate();
//        } catch (Exception e) {
//
//        }
//    }

    public void subtractQuantityFromCartRefProduct(Long cartRefProductId, int quantity) throws SQLException {

        try {
            String query = "UPDATE " + tableName + " SET quantity = quantity - ? WHERE id = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement = connection.prepareStatement(query);
            statement.setInt(1, quantity);
            statement.setLong(2, cartRefProductId);
            statement.executeUpdate();
        } catch (Exception e) {

        }
    }
}
