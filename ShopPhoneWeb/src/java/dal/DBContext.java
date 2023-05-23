package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    protected Connection connection;
    
    public DBContext() {
        try {
            // Chỉnh sửa URL, username, password để xác thực với PostgreSQL
            String url = "jdbc:postgresql://localhost:5432/CartDB";
            String username = "postgres";
            String password = "Huykhoi2010!";
            
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection to SQL success");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }
}

