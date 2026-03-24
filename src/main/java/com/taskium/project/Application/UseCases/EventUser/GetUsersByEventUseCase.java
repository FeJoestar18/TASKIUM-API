package com.taskium.project.Application.UseCases.EventUser;

import com.taskium.project.Application.DTO.EventUser.EventUserResponseDTO;
import com.taskium.project.Domain.Interfaces.Services.EventUser.IEventUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUsersByEventUseCase {

    private final IEventUserService eventUserService;

    public GetUsersByEventUseCase(IEventUserService eventUserService) {
        this.eventUserService = eventUserService;
    }

    public List<EventUserResponseDTO> execute(Long eventId, String authenticatedEmail) {
        return eventUserService.getUsersByEvent(eventId, authenticatedEmail)
                .stream()
                .map(EventUserResponseDTO::from)
                .toList();
    }
}
