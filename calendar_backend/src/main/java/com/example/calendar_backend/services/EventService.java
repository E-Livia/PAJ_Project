package com.example.calendar_backend.services;

import com.example.calendar_backend.models.Event;
import com.example.calendar_backend.services.Database.DatabaseConnection;

import java.sql.*;

public class EventService {

    private UserService userService; // Injectăm UserService

    public EventService() {
        this.userService = new UserService();
    }

    public int createEvent(Event event) throws SQLException {
        String sqlAddEvent = "{CALL add_event(?, ?, ?, ?, ?, ?, ?, ?)}";
        int eventId = 0;

        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement stmtAddEvent = connection.prepareCall(sqlAddEvent)) {


            // Adăugăm parametrii pentru procedura stocată add_event
            stmtAddEvent.setInt(1, event.getUserId()); // ID-ul utilizatorului
            stmtAddEvent.setString(2, event.getTitle());
            stmtAddEvent.setString(3, event.getDescription());
            stmtAddEvent.setDate(4, (Date) event.getStartDate());
            stmtAddEvent.setTime(5, (Time) event.getStartTime());
            stmtAddEvent.setDate(6, (Date) event.getEndDate());
            stmtAddEvent.setTime(7, (Time) event.getEndTime());
            stmtAddEvent.setInt(8, event.getLocationId());

            stmtAddEvent.executeUpdate();
            // Obținem id-ul evenimentului creat (dacă este necesar)
            ResultSet rsAddEvent = stmtAddEvent.getGeneratedKeys();
            if (rsAddEvent.next()) {
                eventId = rsAddEvent.getInt(1);
            }
        }

        return eventId;
    }
}
