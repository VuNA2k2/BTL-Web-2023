package com.example.webs2023.config;

import com.example.webs2023.base.DependencyInjector;
import com.example.webs2023.entity.UserEntity;
import com.example.webs2023.repository.UserRepository;
import com.example.webs2023.service.user.UserService;
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
            DependencyInjector.registerDependency(UserService.class, new UserService(DependencyInjector.getDependency(UserRepository.class)));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        DependencyInjector.destroy();
    }
}

