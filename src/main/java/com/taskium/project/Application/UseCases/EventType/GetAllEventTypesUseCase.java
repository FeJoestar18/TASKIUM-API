package com.taskium.project.Application.UseCases.EventType;

import com.taskium.project.Application.DTO.EventType.EventTypeResponseDTO;
import com.taskium.project.Domain.Interfaces.Services.EventType.IEventTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllEventTypesUseCase {

    private final IEventTypeService eventTypeService;

    public GetAllEventTypesUseCase(IEventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    public List<EventTypeResponseDTO> execute() {
        return eventTypeService.getAll()
                .stream()
                .map(EventTypeResponseDTO::from)
                .toList();
    }
}
