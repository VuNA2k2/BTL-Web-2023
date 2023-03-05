package com.example.webs2023.base;

import com.example.webs2023.config.DatabaseConnection;
import com.example.webs2023.utils.StringUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BaseRepository<E, T> {

    private final Connection connection;
    private final Class<E> entityClass;
    private final String tableName;

    public BaseRepository(Class<E> entityClass) throws SQLException, ClassNotFoundException {
        connection = DatabaseConnection.getInstance();
        this.entityClass = entityClass;
        tableName = StringUtils.camelToSnake(entityClass.getName().toLowerCase().substring(entityClass.getName().lastIndexOf(".") + 1, entityClass.getName().indexOf("Entity"))) + "s";
    }

    public <E> E save(E e) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String sql = "INSERT INTO " + tableName + " ? VALUES ?";
        try {

            String columnLabel = "(";
            String columnValues = "(";
            List<String> columnLabelList = new ArrayList<>();
            List<Object> columnValueList = new ArrayList<>();
            Arrays.stream(entityClass.getDeclaredFields()).map(Field::getName).forEach((name) -> {
                try {
                    PropertyDescriptor valueDescriptor = new PropertyDescriptor(name, entityClass);
                    Method readMethod = valueDescriptor.getReadMethod();
                    Object data = readMethod.invoke(e);
                    if (data != null) {
                        columnValueList.add(data);
                        columnLabelList.add(StringUtils.camelToSnake(name));
                    }
                } catch (IntrospectionException | InvocationTargetException | IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }

            });
            for (int i = 0; i < columnLabelList.size() - 1; i++) {
                columnLabel += columnLabelList.get(i) + ", ";
                columnValues += "?, ";
            }
            columnValues += "?)";
            columnLabel += columnLabelList.get(columnLabelList.size() - 1) + ")";
            sql = sql.replaceFirst("\\?", columnLabel);
            sql = sql.replaceFirst("\\?", columnValues);
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < columnValueList.size(); i++) {
                if (columnValueList.get(i) instanceof String) {
                    preparedStatement.setString(i + 1, columnValueList.get(i).toString());
                } else if (columnValueList.get(i) instanceof Date) {
                    preparedStatement.setDate(i + 1, (Date) columnValueList.get(i));
                } else if (columnValueList.get(i) instanceof Long) {
                    preparedStatement.setLong(i + 1, (Long) columnValueList.get(i));
                } else if (columnValueList.get(i) instanceof Integer) {
                    preparedStatement.setInt(i + 1, (Integer) columnValueList.get(i));
                } else if (columnValueList.get(i) instanceof Timestamp) {
                    preparedStatement.setTimestamp(i + 1, (Timestamp) columnValueList.get(i));
                }
            }
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) return getEntityFromResultSet(resultSet);
            preparedStatement.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    public <E, T> E getById(T id) throws NoSuchMethodException, InvocationTargetException, InstantiationException, SQLException, IllegalAccessException {
        E entity;
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, (Long) id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) entity = getEntityFromResultSet(resultSet);
        else {
            preparedStatement.close();
            return null;
        }
        preparedStatement.close();
        return entity;
    }

    public <E> List<E> getAll() throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<E> listEntity = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            listEntity.add(getEntityFromResultSet(resultSet));
        }
        preparedStatement.close();
        return listEntity;
    }

//    public <E, T> E updateById(T id, E entity) throws SQLException {
//        String sql = "UPDATE " + tableName + " SET ? WHERE id=?";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//
//    }

    protected <E> E getEntityFromResultSet(ResultSet resultSet) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        E entity = (E) entityClass.getDeclaredConstructor().newInstance();
        Arrays.stream(entityClass.getDeclaredFields()).collect(Collectors.toMap(Field::getName, Function.identity())).forEach((name, field) -> {
            try {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(name, entityClass);
                Method setMethod = propertyDescriptor.getWriteMethod();
                if (field.getType().equals(String.class)) {
                    setMethod.invoke(entity, resultSet.getString(StringUtils.camelToSnake(name)));
                } else if (field.getType().equals(Long.class)) {
                    setMethod.invoke(entity, resultSet.getLong(StringUtils.camelToSnake(name)));
                } else if (field.getType().equals(Date.class)) {
                    setMethod.invoke(entity, resultSet.getDate(StringUtils.camelToSnake(name)));
                } else if (field.getType().equals(Timestamp.class)) {
                    setMethod.invoke(entity, resultSet.getTimestamp(StringUtils.camelToSnake(name)));
                }
            } catch (IntrospectionException | SQLException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        return entity;
    }
}
