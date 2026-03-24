package com.taskium.project.Application.UseCases.EventUser;

import com.taskium.project.Domain.Interfaces.Services.EventUser.IEventUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RemoveUserFromEventUseCase {

    private final IEventUserService eventUserService;

    public RemoveUserFromEventUseCase(IEventUserService eventUserService) {
        this.eventUserService = eventUserService;
    }

    @Transactional
    public void execute(Long eventId, Long userId, String authenticatedEmail) {
        eventUserService.removeUserFromEvent(eventId, userId, authenticatedEmail);
    }
}
