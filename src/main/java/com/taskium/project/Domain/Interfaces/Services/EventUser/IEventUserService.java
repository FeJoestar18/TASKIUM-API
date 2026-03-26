package com.taskium.project.Domain.Interfaces.Services.EventUser;

import com.taskium.project.Domain.Entity.EventUser;

import java.util.List;

public interface IEventUserService {
    EventUser addUserToEvent(Long eventId, Long userId, Long participationStatusId, String authenticatedEmail);
    EventUser validateAndGetEventUserForRemoval(Long eventId, Long userId, String authenticatedEmail);
    EventUser updateParticipationStatus(Long eventId, Long userId, Long participationStatusId, String authenticatedEmail);
    List<EventUser> getUsersByEvent(Long eventId, String authenticatedEmail);
    List<EventUser> getEventsByUser(Long userId, String authenticatedEmail);
}
