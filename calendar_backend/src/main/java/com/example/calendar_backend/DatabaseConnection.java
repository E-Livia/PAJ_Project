package com.example.calendar_backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://db-calendar-app.c72uccwwakb7.us-east-1.rds.amazonaws.com:3306/db-calendar-app";
    private static final String USER = "admin";
    private static final String PASSWORD = "rootadmin";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
