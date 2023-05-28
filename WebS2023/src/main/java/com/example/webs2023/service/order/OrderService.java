package com.example.webs2023.service.order;

import com.example.webs2023.dto.order.OrderInput;
import com.example.webs2023.dto.order.OrderOutput;
import com.example.webs2023.entity.OrderEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    OrderOutput createNewOrder(Long userId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;


    OrderOutput getOrderOutputFromOrderEntity(OrderEntity orderEntity) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    List<OrderOutput> getOrderByUserId(Long userId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    List<OrderOutput> getAllListOrder() throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    OrderOutput cancelOrder(Long userId, Long orderId, OrderInput orderInput) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    OrderOutput updateOrderStatus(Long id, String status) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
