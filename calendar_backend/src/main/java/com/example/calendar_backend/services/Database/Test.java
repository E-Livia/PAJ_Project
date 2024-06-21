package com.example.calendar_backend.services.Database;

import com.example.calendar_backend.services.Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Test {

    public static void main(String[] args) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            System.out.println("Conexiunea la baza de date a fost stabilită cu succes!");
            conn.close();
        } catch (SQLException e) {
            System.err.println("Eroare la conectarea la baza de date: " + e.getMessage());
        }
    }
}

