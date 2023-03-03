package com.example.webs2023.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection initializeDatabase () throws ClassNotFoundException, SQLException {
        String dbDriver = "org.postgresql.Driver";
        String dbURL = "jdbc:postgresql://localhost:5432/";

        String dbName = "phone_selling";
        String dbUsername = "postgres";
        String dbPassword = "20092002";

        Class.forName(dbDriver);
        Connection con = DriverManager.getConnection(dbURL + dbName,
                dbUsername,
                dbPassword);
        return con;
    }
}
