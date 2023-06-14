package com.example.webs2023.repository;

import com.example.webs2023.base.BaseRepository;
import com.example.webs2023.entity.ProductEntity;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductRepository extends BaseRepository<ProductEntity, Long> {
    public ProductRepository(Class<ProductEntity> entityClass) throws SQLException, ClassNotFoundException {
        super(entityClass);
    }

    public void deleteByCategoryId(Long categoryId) throws SQLException {
        String sql = "DELETE FROM " + tableName + " WHERE category_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, (Long) categoryId);
        preparedStatement.executeUpdate();
        System.out.println(preparedStatement);
        preparedStatement.close();
    }
}
