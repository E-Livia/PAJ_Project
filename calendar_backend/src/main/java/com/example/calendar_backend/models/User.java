package com.example.calendar_backend.models;

import java.sql.Timestamp;
import java.util.Date;

public class User {
    private int userId;
    private String username;
    private String password;
    private String email;
    private Timestamp createdAt;
    private String firstName;
    private String lastName;
    private Date birthdate;

    public User() {
    }

    public User(int userId, String username, String password, String email, Timestamp createdAt, String firstName, String lastName, Date birthdate) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
    }

    // Getter și Setter pentru userId
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Getter și Setter pentru username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter și Setter pentru password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter și Setter pentru email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter și Setter pentru createdAt
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    // Getter și Setter pentru firstName
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter și Setter pentru lastName
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter și Setter pentru birthdate
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    // Metoda toString pentru a afișa informațiile despre utilizator
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}

