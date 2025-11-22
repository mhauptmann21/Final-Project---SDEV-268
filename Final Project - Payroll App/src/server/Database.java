package server;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/payroll_system";
    private static final String USER = "root";
    private static final String PASS = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

