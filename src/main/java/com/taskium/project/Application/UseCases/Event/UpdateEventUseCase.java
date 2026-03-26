package com.taskium.project.Application.UseCases.Event;

import com.taskium.project.Application.DTO.Event.EventResponseDTO;
import com.taskium.project.Application.DTO.Event.UpdateEventRequestDTO;
import com.taskium.project.Domain.Interfaces.Repository.IEventRepository;
import com.taskium.project.Domain.Interfaces.Services.Event.IEventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateEventUseCase {

    private final IEventService eventService;
    private final IEventRepository eventRepository;

    public UpdateEventUseCase(IEventService eventService, IEventRepository eventRepository) {
        this.eventService = eventService;
        this.eventRepository = eventRepository;
    }

    @Transactional
    public EventResponseDTO execute(Long id, UpdateEventRequestDTO dto, String authenticatedEmail) {
        var event = eventService.updateEvent(
                id, 
                dto.getName(), 
                dto.getDescription(), 
                dto.getStartDate(), 
                dto.getEndDate(), 
                dto.getLocation(), 
                dto.getEventTypeId(), 
                authenticatedEmail
        );
        event = eventRepository.save(event);
        return EventResponseDTO.from(event);
    }
}
