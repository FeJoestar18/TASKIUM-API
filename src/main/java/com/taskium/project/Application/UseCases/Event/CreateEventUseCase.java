package com.taskium.project.Application.UseCases.Event;

import com.taskium.project.Application.DTO.Event.CreateEventRequestDTO;
import com.taskium.project.Application.DTO.Event.EventResponseDTO;
import com.taskium.project.Domain.Interfaces.Services.Event.IEventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateEventUseCase {

    private final IEventService eventService;

    public CreateEventUseCase(IEventService eventService) {
        this.eventService = eventService;
    }

    @Transactional
    public EventResponseDTO execute(CreateEventRequestDTO dto, String authenticatedEmail) {
        return EventResponseDTO.from(eventService.createEvent(dto, authenticatedEmail));
    }
}
