package com.taskium.project.Application.UseCases.EventUser;

import com.taskium.project.Domain.Interfaces.Repository.IEventUserRepository;
import com.taskium.project.Domain.Interfaces.Services.EventUser.IEventUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RemoveUserFromEventUseCase {

    private final IEventUserService eventUserService;
    private final IEventUserRepository eventUserRepository;

    public RemoveUserFromEventUseCase(IEventUserService eventUserService, IEventUserRepository eventUserRepository) {
        this.eventUserService = eventUserService;
        this.eventUserRepository = eventUserRepository;
    }

    @Transactional
    public void execute(Long eventId, Long userId, String authenticatedEmail) {
        var eventUser = eventUserService.validateAndGetEventUserForRemoval(eventId, userId, authenticatedEmail);
        eventUserRepository.delete(eventUser);
    }
}
