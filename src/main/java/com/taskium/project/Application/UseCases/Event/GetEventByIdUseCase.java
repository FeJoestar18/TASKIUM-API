package com.taskium.project.Application.UseCases.Event;

import com.taskium.project.Application.DTO.Event.EventResponseDTO;
import com.taskium.project.Domain.Interfaces.Services.Event.IEventService;
import org.springframework.stereotype.Service;

@Service
public class GetEventByIdUseCase {

    private final IEventService eventService;

    public GetEventByIdUseCase(IEventService eventService) {
        this.eventService = eventService;
    }

    public EventResponseDTO execute(Long id) {
        return EventResponseDTO.from(eventService.getEventById(id));
    }
}
