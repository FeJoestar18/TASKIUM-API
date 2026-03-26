package com.taskium.project.Application.UseCases.EventUser;

import com.taskium.project.Application.DTO.EventUser.AddUserToEventRequestDTO;
import com.taskium.project.Application.DTO.EventUser.EventUserResponseDTO;
import com.taskium.project.Domain.Interfaces.Repository.IEventUserRepository;
import com.taskium.project.Domain.Interfaces.Services.EventUser.IEventUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddUserToEventUseCase {

    private final IEventUserService eventUserService;
    private final IEventUserRepository eventUserRepository;

    public AddUserToEventUseCase(IEventUserService eventUserService, IEventUserRepository eventUserRepository) {
        this.eventUserService = eventUserService;
        this.eventUserRepository = eventUserRepository;
    }

    @Transactional
    public EventUserResponseDTO execute(Long eventId, AddUserToEventRequestDTO dto, String authenticatedEmail) {
        var eventUser = eventUserService.addUserToEvent(eventId, dto.getUserId(), dto.getParticipationStatusId(), authenticatedEmail);
        eventUser = eventUserRepository.save(eventUser);
        return EventUserResponseDTO.from(eventUser);
    }
}
