package com.taskium.project.Application.UseCases.EventUser;

import com.taskium.project.Application.DTO.EventUser.AddUserToEventRequestDTO;
import com.taskium.project.Application.DTO.EventUser.EventUserResponseDTO;
import com.taskium.project.Domain.Interfaces.Services.EventUser.IEventUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddUserToEventUseCase {

    private final IEventUserService eventUserService;

    public AddUserToEventUseCase(IEventUserService eventUserService) {
        this.eventUserService = eventUserService;
    }

    @Transactional
    public EventUserResponseDTO execute(Long eventId, AddUserToEventRequestDTO dto, String authenticatedEmail) {
        return EventUserResponseDTO.from(eventUserService.addUserToEvent(eventId, dto, authenticatedEmail));
    }
}
