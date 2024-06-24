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
        String sqlAddEvent = "{CALL add_event(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
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
            stmtAddEvent.setString(9, event.getDocString());


            stmtAddEvent.executeUpdate();
            // Obținem id-ul evenimentului creat (dacă este necesar)
            ResultSet rsAddEvent = stmtAddEvent.getGeneratedKeys();
            if (rsAddEvent.next()) {
                eventId = rsAddEvent.getInt(1);
            }
        }

        return eventId;
    }


    public void addDocString(String newDoc,String docString){
        if(docString.equals(""))
            docString=newDoc;
        else
            docString=docString+","+newDoc;
    }
    public void deleteDocString(String deleteDoc,String docString){
        if(docString.equals(deleteDoc))
            docString="";
        else
            deleteWord(docString,deleteDoc);
    }

    public static String deleteWord(String input, String word) {
        if (input == null || word == null || input.isEmpty()) {
            return input;
        }

        String[] words = input.split(",");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (!words[i].equals(word)) {
                if (result.length() > 0) {
                    result.append(",");
                }
                result.append(words[i]);
            }
        }

        return result.toString();
    }
}
