package com.taskium.project.Application.UseCases.Event;

import com.taskium.project.Domain.Interfaces.Repository.IEventRepository;
import com.taskium.project.Domain.Interfaces.Services.Event.IEventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteEventUseCase {

    private final IEventService eventService;
    private final IEventRepository eventRepository;

    public DeleteEventUseCase(IEventService eventService, IEventRepository eventRepository) {
        this.eventService = eventService;
        this.eventRepository = eventRepository;
    }

    @Transactional
    public void execute(Long id, String authenticatedEmail) {
        eventService.validateEventDeletion(id, authenticatedEmail);
        eventRepository.deleteById(id);
    }
}
