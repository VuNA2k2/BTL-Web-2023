package com.example.webs2023.config;

import com.example.webs2023.base.DependencyInjector;
import com.example.webs2023.entity.*;
import com.example.webs2023.repository.*;
import com.example.webs2023.service.auth.AuthService;
import com.example.webs2023.service.auth.AuthServiceImpl;
import com.example.webs2023.service.cart.CartRefProductService;
import com.example.webs2023.service.cart.CartRefProductServiceImpl;
import com.example.webs2023.service.cart.CartService;
import com.example.webs2023.service.cart.CartServiceImpl;
import com.example.webs2023.service.category.CategoryService;
import com.example.webs2023.service.jwt.JwtService;
import com.example.webs2023.service.jwt.JwtServiceImpl;
import com.example.webs2023.service.product.ProductService;
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
            DependencyInjector.registerDependency(UserRepository.class, new UserRepository(UserEntity.class));
            DependencyInjector.registerDependency(UserService.class, new UserServiceImpl(DependencyInjector.getDependency(UserRepository.class)));
            DependencyInjector.registerDependency(JwtService.class, new JwtServiceImpl(DependencyInjector.getDependency(UserRepository.class)));
            DependencyInjector.registerDependency(AuthService.class, new AuthServiceImpl(DependencyInjector.getDependency(UserRepository.class), DependencyInjector.getDependency(JwtService.class)));
            DependencyInjector.registerDependency(CategoryRepository.class, new CategoryRepository(CategoryEntity.class));
            DependencyInjector.registerDependency(CategoryService.class, new CategoryService(DependencyInjector.getDependency(CategoryRepository.class)));
            DependencyInjector.registerDependency(ProductRepository.class, new ProductRepository(ProductEntity.class));
            DependencyInjector.registerDependency(ProductService.class, new ProductService(DependencyInjector.getDependency(ProductRepository.class)));
            DependencyInjector.registerDependency(CartRepository.class, new CartRepository(CartEntity.class));
            DependencyInjector.registerDependency(CartRefProductRepository.class, new CartRefProductRepository(CartRefProductEntity.class));
            DependencyInjector.registerDependency(CartRefProductService.class, new CartRefProductServiceImpl(DependencyInjector.getDependency(CartRefProductRepository.class)));
            DependencyInjector.registerDependency(CartService.class, new CartServiceImpl(DependencyInjector.getDependency(CartRepository.class), DependencyInjector.getDependency(CartRefProductService.class)));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        DependencyInjector.destroy();
    }
}

