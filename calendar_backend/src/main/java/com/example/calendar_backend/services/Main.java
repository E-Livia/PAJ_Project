package com.example.calendar_backend.services;

import com.example.calendar_backend.models.User;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();

        // Crează un nou utilizator folosind constructorul cu parametri
        LocalDate birthday = LocalDate.of(2001, 7, 17);
        User newUser = new User(
                0,
                "madalina_serban",
                "password123",
                "madalina_serban@example.com",
                null, // createdAt va fi setat automat de baza de date
                "Madalina",
                "Serban",
                java.sql.Date.valueOf(birthday)
        );

        try {
            // Adaugă utilizatorul în baza de date
            userService.createUser(newUser);
            System.out.println("User added successfully!");

            // Citește utilizatorul din baza de date
            User retrievedUser = userService.getUserById(newUser.getUserId());
            System.out.println("Retrieved User: " + retrievedUser);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
