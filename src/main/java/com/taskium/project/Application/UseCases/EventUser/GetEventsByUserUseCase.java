package com.taskium.project.Application.UseCases.EventUser;

import com.taskium.project.Application.DTO.EventUser.EventUserResponseDTO;
import com.taskium.project.Domain.Interfaces.Services.EventUser.IEventUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetEventsByUserUseCase {

    private final IEventUserService eventUserService;

    public GetEventsByUserUseCase(IEventUserService eventUserService) {
        this.eventUserService = eventUserService;
    }

    public List<EventUserResponseDTO> execute(Long userId, String authenticatedEmail) {
        return eventUserService.getEventsByUser(userId, authenticatedEmail)
                .stream()
                .map(EventUserResponseDTO::from)
                .toList();
    }
}
