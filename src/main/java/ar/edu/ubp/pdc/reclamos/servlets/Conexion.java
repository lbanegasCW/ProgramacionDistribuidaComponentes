package ar.edu.ubp.pdc.reclamos.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS:54106;databaseName=pdc;encrypt=false;integratedSecurity=false";
    private static final String USERNAME = "test";
    private static final String PASSWORD = "test";

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load SQL Server JDBC driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}