package com.taskium.project.Domain.Interfaces.Services.Event;

import com.taskium.project.Application.DTO.Event.CreateEventRequestDTO;
import com.taskium.project.Application.DTO.Event.UpdateEventRequestDTO;
import com.taskium.project.Domain.Entity.Event;

import java.util.List;

public interface IEventService {
    Event createEvent(CreateEventRequestDTO dto, String authenticatedEmail);
    Event updateEvent(Long id, UpdateEventRequestDTO dto, String authenticatedEmail);
    void deleteEvent(Long id, String authenticatedEmail);
    List<Event> getAllEvents();
    Event getEventById(Long id);
}
