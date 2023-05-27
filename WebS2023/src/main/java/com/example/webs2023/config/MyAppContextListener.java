package com.example.webs2023.config;

import com.example.webs2023.base.DependencyInjector;
import com.example.webs2023.entity.*;
import com.example.webs2023.repository.*;
import com.example.webs2023.service.auth.AuthService;
import com.example.webs2023.service.auth.AuthServiceImpl;
import com.example.webs2023.service.cart.CartService;
import com.example.webs2023.service.cart.CartServiceImpl;
import com.example.webs2023.service.cart_ref_product.CartRefProductService;
import com.example.webs2023.service.cart_ref_product.CartRefProductServiceImpl;
import com.example.webs2023.service.category.CategoryService;
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
            DependencyInjector.registerDependency(ImageRepository.class, new ImageRepository(ImageEntity.class));
            DependencyInjector.registerDependency(ImageService.class, new ImageServiceImpl(DependencyInjector.getDependency(ImageRepository.class)));
            DependencyInjector.registerDependency(UserRepository.class, new UserRepository(UserEntity.class));
            DependencyInjector.registerDependency(UserService.class, new UserServiceImpl(DependencyInjector.getDependency(UserRepository.class)));
            DependencyInjector.registerDependency(JwtService.class, new JwtServiceImpl(DependencyInjector.getDependency(UserRepository.class)));
            DependencyInjector.registerDependency(AuthService.class, new AuthServiceImpl(DependencyInjector.getDependency(UserRepository.class), DependencyInjector.getDependency(JwtService.class)));
            DependencyInjector.registerDependency(CategoryRepository.class, new CategoryRepository(CategoryEntity.class));
            DependencyInjector.registerDependency(CategoryService.class, new CategoryService(DependencyInjector.getDependency(CategoryRepository.class)));
            DependencyInjector.registerDependency(ProductRepository.class, new ProductRepository(ProductEntity.class));
            DependencyInjector.registerDependency(ProductService.class, new ProductServiceImpl(DependencyInjector.getDependency(ProductRepository.class), DependencyInjector.getDependency(CategoryService.class), DependencyInjector.getDependency(ImageService.class)));
            DependencyInjector.registerDependency(CartRepository.class, new CartRepository(CartEntity.class));
            DependencyInjector.registerDependency(CartRefProductRepository.class, new CartRefProductRepository(CartsRefProductEntity.class));
            DependencyInjector.registerDependency(CartRefProductService.class, new CartRefProductServiceImpl(DependencyInjector.getDependency(CartRefProductRepository.class), DependencyInjector.getDependency(ProductService.class)));
            DependencyInjector.registerDependency(CartService.class, new CartServiceImpl(DependencyInjector.getDependency(CartRepository.class), DependencyInjector.getDependency(CartRefProductService.class), DependencyInjector.getDependency(ProductService.class)));
            DependencyInjector.registerDependency(OrderRepository.class, new OrderRepository(OrderEntity.class));
            DependencyInjector.registerDependency(ProductInOrderRepository.class, new ProductInOrderRepository(ProductsInOrderEntity.class));
            DependencyInjector.registerDependency(ProductInOrderService.class, new ProductInOrderServiceImpl(DependencyInjector.getDependency(ProductInOrderRepository.class)));
            DependencyInjector.registerDependency(OrderService.class, new OrderServiceImpl(DependencyInjector.getDependency(OrderRepository.class), DependencyInjector.getDependency(CartService.class), DependencyInjector.getDependency(ProductInOrderService.class)));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        DependencyInjector.destroy();
    }
}

