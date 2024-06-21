package com.example.calendar_backend.services.CrudTests;

import com.example.calendar_backend.models.Event;
import com.example.calendar_backend.models.User;
import com.example.calendar_backend.services.EventService;
import com.example.calendar_backend.services.UserService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class EventTest {
    public static void main(String[] args) {
        EventService eventService = new EventService();
        UserService userService = new UserService();

        try {
            // Obține ID-ul utilizatorului folosind UserService
           User user = userService.getUserByUsername("john_doe"); // înlocuiește cu username-ul dorit
            int userId=user.getUserId();

            // Exemplu de adăugare a unui eveniment
            LocalDate startDate = LocalDate.of(2024, 6, 25);
            LocalTime startTime = LocalTime.of(10, 0);
            LocalDate endDate = LocalDate.of(2024, 6, 25);
            LocalTime endTime = LocalTime.of(12, 0);

            Event newEvent = new Event(0,
                    userId,
                    "Meeting",
                    "Team meeting for project planning",
                    java.sql.Date.valueOf(startDate),
                    java.sql.Time.valueOf(startTime),
                    java.sql.Date.valueOf(endDate),
                    java.sql.Time.valueOf(endTime),
                    1 // locationId - înlocuiește cu locația dorită
            );

            eventService.createEvent(newEvent); // înlocuiește cu username-ul dorit
            System.out.println("Event added successfully " );

            // Exemplu de actualizare a unui eveniment
//            newEvent.setTitle("Updated Meeting");
//            newEvent.setDescription("Updated team meeting");
//            eventService.updateEvent(newEvent);
//            System.out.println("Event updated successfully!");

            // Exemplu de ștergere a unui eveniment
//            eventService.deleteEvent(eventId);
//            System.out.println("Event deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
