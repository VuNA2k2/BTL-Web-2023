package com.example.webs2023.repository;

import com.example.webs2023.base.BaseRepository;
import com.example.webs2023.entity.CartRefProductEntity;
import com.example.webs2023.config.DatabaseConnection;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class CartRefProductRepository extends BaseRepository<CartRefProductEntity, Long> {

    public CartRefProductRepository(Class<CartRefProductEntity> entityClass) throws SQLException, ClassNotFoundException {
        super(entityClass);
    }

    public List<CartRefProductEntity> getCartRefProductsByCartId(Long cartId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return rawQuery("SELECT * FROM " + tableName + " WHERE cartId = " + cartId);
    }

    public void deleteByCartId(Long cartId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnection.getInstance();
            String query = "DELETE FROM " + tableName + " WHERE cartId = ?";
            statement = connection.prepareStatement(query);
            statement.setLong(1, cartId);
            statement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
