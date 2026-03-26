package com.taskium.project.Application.UseCases.EventUser;

import com.taskium.project.Application.DTO.EventUser.EventUserResponseDTO;
import com.taskium.project.Application.DTO.EventUser.UpdateParticipationStatusDTO;
import com.taskium.project.Domain.Interfaces.Repository.IEventUserRepository;
import com.taskium.project.Domain.Interfaces.Services.EventUser.IEventUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateEventUserParticipationStatusUseCase {

    private final IEventUserService eventUserService;
    private final IEventUserRepository eventUserRepository;

    public UpdateEventUserParticipationStatusUseCase(IEventUserService eventUserService, IEventUserRepository eventUserRepository) {
        this.eventUserService = eventUserService;
        this.eventUserRepository = eventUserRepository;
    }

    @Transactional
    public EventUserResponseDTO execute(Long eventId, Long userId, UpdateParticipationStatusDTO dto, String authenticatedEmail) {
        var eventUser = eventUserService.updateParticipationStatus(eventId, userId, dto.getParticipationStatusId(), authenticatedEmail);
        eventUserRepository.save(eventUser);
        return EventUserResponseDTO.from(eventUser);
    }
}
