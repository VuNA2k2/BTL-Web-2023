package com.example.webs2023.service.order;

import com.example.webs2023.base.BaseService;
import com.example.webs2023.dto.cart.CartDetailOutput;
import com.example.webs2023.dto.order.OrderInput;
import com.example.webs2023.dto.order.OrderOutput;
import com.example.webs2023.entity.OrderEntity;
import com.example.webs2023.repository.OrderRepository;
import com.example.webs2023.service.cart.CartService;
import com.example.webs2023.service.product_in_order.ProductInOrderService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

public class OrderServiceImpl extends BaseService<OrderEntity, Long, OrderInput, OrderOutput> implements OrderService {
    private final CartService cartService;
    private final ProductInOrderService productInOrderService;

    public OrderServiceImpl(OrderRepository repository, CartService cartService, ProductInOrderService productInOrderService) {
        this.cartService = cartService;
        this.productInOrderService = productInOrderService;
        this.repository = repository;
        this.mapper = new OrderMapper(OrderEntity.class, OrderInput.class, OrderOutput.class);
    }

    @Override
    public OrderOutput createNewOrder(Long userId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        CartDetailOutput cartOutput = cartService.getLeastCart(userId);
        if(cartOutput == null) {
            return null;
        }
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserId(userId);
        orderEntity.setOrderDate(Timestamp.from(Calendar.getInstance().toInstant()));
        orderEntity.setStatus("PENDING");
        OrderEntity savedOrder = repository.save(orderEntity);
        cartService.deleteCart(userId);
        cartOutput.getProducts().forEach(cartRefProductOutput -> {
            try {
                productInOrderService.createProductInOrderCartRefProduct(savedOrder.getId(), cartRefProductOutput);
            } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                     IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return getOrderOutputFromOrderEntity(savedOrder);
    }

    @Override
    public OrderOutput getOrderOutputFromOrderEntity(OrderEntity orderEntity) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        OrderOutput orderOutput = mapper.getOutputFromEntity(orderEntity);
        orderOutput.setProducts(productInOrderService.getProductInOrderOutputListByOrderId(orderEntity.getId()));
        Long totalMoney = orderOutput.getProducts().stream().mapToLong(productInOrderOutput -> productInOrderOutput.getProductPrice() * productInOrderOutput.getProductQuantity()).sum();
        orderOutput.setTotalMoney(totalMoney);
        return orderOutput;
    }

    @Override
    public List<OrderOutput> getOrderByUserId(Long userId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<OrderEntity> orderEntities = repository.getAll("WHERE user_id = " + userId + " ORDER BY id DESC");
        List<OrderOutput> orderOutputs = orderEntities.stream().map(orderEntity -> {
            try {
                return getOrderOutputFromOrderEntity(orderEntity);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();
        return orderOutputs;
    }

    @Override
    public List<OrderOutput> getAllListOrder() throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<OrderEntity> orderEntities = repository.getAll("ORDER BY id DESC");
        List<OrderOutput> orderOutputs = orderEntities.stream().map(orderEntity -> {
            try {
                return getOrderOutputFromOrderEntity(orderEntity);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();
        return orderOutputs;
    }
}
