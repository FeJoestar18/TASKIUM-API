package com.taskium.project.Application.UseCases.Event;

import com.taskium.project.Application.DTO.Event.EventResponseDTO;
import com.taskium.project.Domain.Interfaces.Services.Event.IEventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllEventsUseCase {

    private final IEventService eventService;

    public GetAllEventsUseCase(IEventService eventService) {
        this.eventService = eventService;
    }

    public List<EventResponseDTO> execute() {
        return eventService.getAllEvents()
                .stream()
                .map(EventResponseDTO::from)
                .toList();
    }
}
