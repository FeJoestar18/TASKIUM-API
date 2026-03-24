package com.taskium.project.Application.UseCases.Event;

import com.taskium.project.Application.DTO.Event.EventResponseDTO;
import com.taskium.project.Application.DTO.Event.UpdateEventRequestDTO;
import com.taskium.project.Domain.Interfaces.Services.Event.IEventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateEventUseCase {

    private final IEventService eventService;

    public UpdateEventUseCase(IEventService eventService) {
        this.eventService = eventService;
    }

    @Transactional
    public EventResponseDTO execute(Long id, UpdateEventRequestDTO dto, String authenticatedEmail) {
        return EventResponseDTO.from(eventService.updateEvent(id, dto, authenticatedEmail));
    }
}
