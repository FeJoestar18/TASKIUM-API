package com.taskium.project.Domain.Interfaces.Services.Event;

import com.taskium.project.Domain.Entity.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface IEventService {
    Event createEvent(String name, String description, LocalDateTime startDate, LocalDateTime endDate, String location, Long eventTypeId, String authenticatedEmail);
    Event updateEvent(Long id, String name, String description, LocalDateTime startDate, LocalDateTime endDate, String location, Long eventTypeId, String authenticatedEmail);
    void validateEventDeletion(Long id, String authenticatedEmail);
    List<Event> getAllEvents();
    Event getEventById(Long id);
}
