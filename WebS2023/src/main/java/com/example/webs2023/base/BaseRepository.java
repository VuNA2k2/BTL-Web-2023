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

@SuppressWarnings("SqlDialectInspection")
public class BaseRepository<E, T> {

    protected final Connection connection;
    protected final Class<E> entityClass;
    protected final String tableName;

    protected BaseRepository(Class<E> entityClass) throws SQLException, ClassNotFoundException {
        connection = DatabaseConnection.getInstance();
        this.entityClass = entityClass;
        tableName = StringUtils.camelToSnake(entityClass.getName().toLowerCase().substring(entityClass.getName().lastIndexOf(".") + 1, entityClass.getName().indexOf("Entity"))) + "s";
    }

    public E save(E e) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
        String sql = "INSERT INTO " + tableName + " ? VALUES ?";
        StringBuilder columnLabel = new StringBuilder("(");
        StringBuilder columnValues = new StringBuilder("(");
        List<String> columnLabelList = new ArrayList<>();
        List<Object> columnValueList = new ArrayList<>();
        E finalE = e;
        Arrays.stream(entityClass.getDeclaredFields()).map(Field::getName).forEach((name) -> {
            try {
                PropertyDescriptor valueDescriptor = new PropertyDescriptor(name, entityClass);
                Method readMethod = valueDescriptor.getReadMethod();
                Object data = readMethod.invoke(finalE);

                if (data != null) {
                    columnValueList.add(data);
                    columnLabelList.add(StringUtils.camelToSnake(name));
                }
            } catch (IntrospectionException | InvocationTargetException | IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }

        });
        for (int i = 0; i < columnLabelList.size() - 1; i++) {
            columnLabel.append(columnLabelList.get(i)).append(", ");
            columnValues.append("?, ");
        }
        columnValues.append("?)");
        columnLabel.append(columnLabelList.get(columnLabelList.size() - 1)).append(")");
        sql = sql.replaceFirst("\\?", columnLabel.toString());
        sql = sql.replaceFirst("\\?", columnValues.toString());
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
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        System.out.println(preparedStatement);
        if (resultSet.next()) e = getEntityFromResultSet(resultSet);
        else e = null;
        preparedStatement.close();

        return e;
    }

    public E getById(T id) throws NoSuchMethodException, InvocationTargetException, InstantiationException, SQLException, IllegalAccessException {
        E entity;
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, (Long) id);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println(preparedStatement);
        if (resultSet.next()) entity = getEntityFromResultSet(resultSet);
        else {
            preparedStatement.close();
            return null;
        }
        preparedStatement.close();
        return entity;
    }

    public List<E> getAll(String orderQuery) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<E> listEntity = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName + " " + (orderQuery != null ? orderQuery : "");
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println(preparedStatement);
        while (resultSet.next()) {
            listEntity.add(getEntityFromResultSet(resultSet));
        }
        preparedStatement.close();
        return listEntity;
    }

    public List<E> getAll() throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return this.getAll(null);
    }

    public E updateById(T id, E entity) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String sql = "UPDATE " + tableName + " SET ? WHERE id=?";
        List<String> columnLabelList = new ArrayList<>();
        List<Object> columnValueList = new ArrayList<>();
        E finalEntity = entity;
        Arrays.stream(entityClass.getDeclaredFields()).map(Field::getName).forEach((name) -> {
            try {
                PropertyDescriptor valueDescriptor = new PropertyDescriptor(name, entityClass);
                Method readMethod = valueDescriptor.getReadMethod();
                Object data = readMethod.invoke(finalEntity);
                if (data != null) {
                    columnValueList.add(data);
                    columnLabelList.add(StringUtils.camelToSnake(name));
                }
            } catch (IntrospectionException | InvocationTargetException | IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        });
        StringBuilder listSet = new StringBuilder();
        for (int i = 0; i < columnLabelList.size() - 1; i++) {
            listSet.append(columnLabelList.get(i)).append("=?, ");
        }
        listSet.append(columnLabelList.get(columnLabelList.size() - 1)).append("=?");
        sql = sql.replaceFirst("\\?", listSet.toString());
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
        preparedStatement.setLong(columnValueList.size() + 1, (Long) id);
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        System.out.println(preparedStatement);
        if (resultSet.next()) {
            entity = getEntityFromResultSet(resultSet);
            preparedStatement.close();
            return entity;
        }
        preparedStatement.close();
        return null;
    }

    protected E getEntityFromResultSet(ResultSet resultSet) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        E entity = entityClass.getDeclaredConstructor().newInstance();
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

    public List<E> rawQuery(String sql) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<E> listEntity = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println(preparedStatement);
        while (resultSet.next()) {
            listEntity.add(getEntityFromResultSet(resultSet));
        }
        preparedStatement.close();
        return listEntity;
    }
}
