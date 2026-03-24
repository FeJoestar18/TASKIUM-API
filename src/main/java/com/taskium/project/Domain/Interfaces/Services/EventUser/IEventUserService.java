package com.taskium.project.Domain.Interfaces.Services.EventUser;

import com.taskium.project.Application.DTO.EventUser.AddUserToEventRequestDTO;
import com.taskium.project.Application.DTO.EventUser.UpdateParticipationStatusDTO;
import com.taskium.project.Domain.Entity.EventUser;

import java.util.List;

public interface IEventUserService {
    EventUser addUserToEvent(Long eventId, AddUserToEventRequestDTO dto, String authenticatedEmail);
    void removeUserFromEvent(Long eventId, Long userId, String authenticatedEmail);
    EventUser updateParticipationStatus(Long eventId, Long userId, UpdateParticipationStatusDTO dto, String authenticatedEmail);
    List<EventUser> getUsersByEvent(Long eventId, String authenticatedEmail);
    List<EventUser> getEventsByUser(Long userId, String authenticatedEmail);
}
