package com.taskium.project.Application.UseCases.EventUser;

import com.taskium.project.Application.DTO.EventUser.EventUserResponseDTO;
import com.taskium.project.Application.DTO.EventUser.UpdateParticipationStatusDTO;
import com.taskium.project.Domain.Interfaces.Services.EventUser.IEventUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateEventUserParticipationStatusUseCase {

    private final IEventUserService eventUserService;

    public UpdateEventUserParticipationStatusUseCase(IEventUserService eventUserService) {
        this.eventUserService = eventUserService;
    }

    @Transactional
    public EventUserResponseDTO execute(Long eventId, Long userId, UpdateParticipationStatusDTO dto, String authenticatedEmail) {
        return EventUserResponseDTO.from(eventUserService.updateParticipationStatus(eventId, userId, dto, authenticatedEmail));
    }
}
