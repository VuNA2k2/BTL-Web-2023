package com.example.webs2023.base;

import com.example.webs2023.config.DatabaseConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.SQLException;

public class BaseController extends HttpServlet {
    protected final Gson GSON = new GsonBuilder().create();
    protected Connection connection;

    @Override
    public void init() throws ServletException {
        try {
            connection = DatabaseConnection.getInstance();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        super.init();
    }
}

