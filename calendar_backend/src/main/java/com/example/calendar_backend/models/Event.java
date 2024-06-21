package com.example.calendar_backend.models;

import java.sql.Time;
import java.util.Date;

public class Event {
    private int eventId;
    private int userId;
    private String title;
    private String description;
    private Date startDate;
    private Time startTime;
    private Date endDate;
    private Time endTime;
    private int locationId;

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", startTime=" + startTime +
                ", endDate=" + endDate +
                ", endTime=" + endTime +
                ", locationId=" + locationId +
                '}';
    }

    public int getEventId() {
        return eventId;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public Event(int eventId, int userId, String title, String description, Date startDate, Time startTime, Date endDate, Time endTime, int locationId) {
        this.eventId = eventId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.locationId = locationId;
    }
}
