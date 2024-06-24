package com.example.calendar_backend.controllers;

import com.example.calendar_backend.models.Event;
import com.example.calendar_backend.services.EventService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;

@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventController {

    private final EventService eventService;

    @Inject
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @POST
    @Path("/create")
    public int createEvent(Event event) throws SQLException {
        return eventService.createEvent(event);
    }
}