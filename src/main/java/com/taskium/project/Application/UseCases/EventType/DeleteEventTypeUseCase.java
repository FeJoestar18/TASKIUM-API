package com.taskium.project.Application.UseCases.EventType;

import com.taskium.project.Domain.Interfaces.Services.EventType.IEventTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteEventTypeUseCase {

    private final IEventTypeService eventTypeService;

    public DeleteEventTypeUseCase(IEventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    @Transactional
    public void execute(Long id) {
        eventTypeService.delete(id);
    }
}
