package com.example.calendar_backend.services;

import com.example.calendar_backend.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    // Create a new user
    public void createUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, email, first_name, last_name, birthdate) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getFirstName());
            pstmt.setString(5, user.getLastName());
            pstmt.setDate(6, new java.sql.Date(user.getBirthdate().getTime()));
            pstmt.executeUpdate();
        }
    }

    // Retrieve a user by ID
    public User getUserById(int userId) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        User user = null;

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getTimestamp("created_at"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("birthdate")
                );
            }
        }

        return user;
    }

    // Retrieve all users
    public List<User> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getTimestamp("created_at"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("birthdate")
                );
                users.add(user);
            }
        }

        return users;
    }

    // Update a user
    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET username = ?, password = ?, email = ?, first_name = ?, last_name = ?, birthdate = ? WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getFirstName());
            pstmt.setString(5, user.getLastName());
            pstmt.setDate(6, new java.sql.Date(user.getBirthdate().getTime()));
            pstmt.setInt(7, user.getUserId());
            pstmt.executeUpdate();
        }
    }

    // Delete a user
    public void deleteUser(int userId) throws SQLException {
        String sql = "DELETE FROM users WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        }
    }
}
