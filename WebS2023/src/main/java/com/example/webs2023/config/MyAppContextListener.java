package com.example.webs2023.config;

import com.example.webs2023.base.ServiceLocator;
import com.example.webs2023.entity.*;
import com.example.webs2023.repository.*;
import com.example.webs2023.service.auth.AuthService;
import com.example.webs2023.service.auth.AuthServiceImpl;
import com.example.webs2023.service.cart.CartService;
import com.example.webs2023.service.cart.CartServiceImpl;
import com.example.webs2023.service.cart_ref_product.CartRefProductService;
import com.example.webs2023.service.cart_ref_product.CartRefProductServiceImpl;
import com.example.webs2023.service.category.CategoryService;
import com.example.webs2023.service.dashboard.DashboardService;
import com.example.webs2023.service.dashboard.DashboardServiceImpl;
import com.example.webs2023.service.image.ImageService;
import com.example.webs2023.service.image.ImageServiceImpl;
import com.example.webs2023.service.jwt.JwtService;
import com.example.webs2023.service.jwt.JwtServiceImpl;
import com.example.webs2023.service.order.OrderService;
import com.example.webs2023.service.order.OrderServiceImpl;
import com.example.webs2023.service.product.ProductService;
import com.example.webs2023.service.product.ProductServiceImpl;
import com.example.webs2023.service.product_in_order.ProductInOrderService;
import com.example.webs2023.service.product_in_order.ProductInOrderServiceImpl;
import com.example.webs2023.service.rate.RateService;
import com.example.webs2023.service.rate.RateServiceImpl;
import com.example.webs2023.service.user.UserService;
import com.example.webs2023.service.user.UserServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener
public class MyAppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            DatabaseConnection.getInstance();
            ServiceLocator.registerDependency(ImageRepository.class, new ImageRepository(ImageEntity.class));
            ServiceLocator.registerDependency(ImageService.class, new ImageServiceImpl(ServiceLocator.getDependency(ImageRepository.class)));
            ServiceLocator.registerDependency(UserRepository.class, new UserRepository(UserEntity.class));
            ServiceLocator.registerDependency(UserService.class, new UserServiceImpl(ServiceLocator.getDependency(UserRepository.class)));
            ServiceLocator.registerDependency(JwtService.class, new JwtServiceImpl(ServiceLocator.getDependency(UserRepository.class)));
            ServiceLocator.registerDependency(CategoryRepository.class, new CategoryRepository(CategoryEntity.class));
            ServiceLocator.registerDependency(CategoryService.class, new CategoryService(ServiceLocator.getDependency(CategoryRepository.class)));
            ServiceLocator.registerDependency(ProductRepository.class, new ProductRepository(ProductEntity.class));
            ServiceLocator.registerDependency(ProductService.class, new ProductServiceImpl(ServiceLocator.getDependency(ProductRepository.class), ServiceLocator.getDependency(CategoryService.class), ServiceLocator.getDependency(ImageService.class)));
            ServiceLocator.registerDependency(CartRepository.class, new CartRepository(CartEntity.class));
            ServiceLocator.registerDependency(CartRefProductRepository.class, new CartRefProductRepository(CartsRefProductEntity.class));
            ServiceLocator.registerDependency(CartRefProductService.class, new CartRefProductServiceImpl(ServiceLocator.getDependency(CartRefProductRepository.class), ServiceLocator.getDependency(ProductService.class)));
            ServiceLocator.registerDependency(CartService.class, new CartServiceImpl(ServiceLocator.getDependency(CartRepository.class), ServiceLocator.getDependency(CartRefProductService.class), ServiceLocator.getDependency(ProductService.class)));
            ServiceLocator.registerDependency(OrderRepository.class, new OrderRepository(OrderEntity.class));
            ServiceLocator.registerDependency(ProductInOrderRepository.class, new ProductInOrderRepository(ProductsInOrderEntity.class));
            ServiceLocator.registerDependency(ProductInOrderService.class, new ProductInOrderServiceImpl(ServiceLocator.getDependency(ProductInOrderRepository.class)));
            ServiceLocator.registerDependency(OrderService.class, new OrderServiceImpl(ServiceLocator.getDependency(OrderRepository.class), ServiceLocator.getDependency(CartService.class), ServiceLocator.getDependency(ProductInOrderService.class)));
            ServiceLocator.registerDependency(RateRepository.class, new RateRepository(RateEntity.class));
            ServiceLocator.registerDependency(RateService.class, new RateServiceImpl(ServiceLocator.getDependency(RateRepository.class), ServiceLocator.getDependency(ProductInOrderRepository.class), ServiceLocator.getDependency(ImageService.class)));
            ServiceLocator.registerDependency(DashboardService.class, new DashboardServiceImpl(ServiceLocator.getDependency(ProductInOrderService.class), ServiceLocator.getDependency(UserService.class), ServiceLocator.getDependency(OrderService.class)));
            ServiceLocator.registerDependency(AuthService.class, new AuthServiceImpl(ServiceLocator.getDependency(UserService.class), ServiceLocator.getDependency(JwtService.class), ServiceLocator.getDependency(CartService.class)));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServiceLocator.destroy();
    }
}

