package com.taskium.project.Application.UseCases.Event;

import com.taskium.project.Domain.Interfaces.Services.Event.IEventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteEventUseCase {

    private final IEventService eventService;

    public DeleteEventUseCase(IEventService eventService) {
        this.eventService = eventService;
    }

    @Transactional
    public void execute(Long id, String authenticatedEmail) {
        eventService.deleteEvent(id, authenticatedEmail);
    }
}
