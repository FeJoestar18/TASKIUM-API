package com.taskium.project.Application.UseCases.EventType;

import com.taskium.project.Application.DTO.EventType.EventTypeRequestDTO;
import com.taskium.project.Application.DTO.EventType.EventTypeResponseDTO;
import com.taskium.project.Domain.Interfaces.Services.EventType.IEventTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateEventTypeUseCase {

    private final IEventTypeService eventTypeService;

    public CreateEventTypeUseCase(IEventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    @Transactional
    public EventTypeResponseDTO execute(EventTypeRequestDTO dto) {
        return EventTypeResponseDTO.from(eventTypeService.create(dto));
    }
}
