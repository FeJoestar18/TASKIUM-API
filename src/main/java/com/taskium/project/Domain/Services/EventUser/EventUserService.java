package com.taskium.project.Domain.Services.EventUser;

import com.taskium.project.Domain.Common.Exceptions.Event.EventNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.Event.EventUserNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.Event.NoEventsFoundException;
import com.taskium.project.Domain.Common.Exceptions.Event.ParticipationStatusNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.Event.UnauthorizedEventActionException;
import com.taskium.project.Domain.Common.Exceptions.Event.UserAlreadyInEventException;
import com.taskium.project.Domain.Common.Exceptions.User.UserNotFoundException;
import com.taskium.project.Domain.Entity.Event;
import com.taskium.project.Domain.Entity.EventUser;
import com.taskium.project.Domain.Entity.ParticipationStatus;
import com.taskium.project.Domain.Entity.User;
import com.taskium.project.Domain.Interfaces.Repository.IEventRepository;
import com.taskium.project.Domain.Interfaces.Repository.IEventUserRepository;
import com.taskium.project.Domain.Interfaces.Repository.IParticipationStatusRepository;
import com.taskium.project.Domain.Interfaces.Repository.IUserRepository;
import com.taskium.project.Domain.Interfaces.Services.EventUser.IEventUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventUserService implements IEventUserService {

    private final IEventUserRepository eventUserRepository;
    private final IEventRepository eventRepository;
    private final IUserRepository userRepository;
    private final IParticipationStatusRepository participationStatusRepository;

    public EventUserService(
            IEventUserRepository eventUserRepository,
            IEventRepository eventRepository,
            IUserRepository userRepository,
            IParticipationStatusRepository participationStatusRepository
    ) {
        this.eventUserRepository = eventUserRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.participationStatusRepository = participationStatusRepository;
    }

    @Override
    public EventUser addUserToEvent(Long eventId, Long targetUserId, Long participationStatusId, String authenticatedEmail) {
        User authenticatedUser = findUserByEmail(authenticatedEmail);
        Event event = findEventById(eventId);
        validateCanManageEventUser(event, targetUserId, authenticatedUser.getId());
        User targetUser = findUserById(targetUserId);
        ParticipationStatus participationStatus = findParticipationStatusById(participationStatusId);

        if (eventUserRepository.existsByUserIdAndEventId(targetUser.getId(), event.getId())) {
            throw new UserAlreadyInEventException(targetUser.getId(), event.getId());
        }

        return EventUser.builder()
                .event(event)
                .user(targetUser)
                .participationStatus(participationStatus)
                .build();
    }

    @Override
    public EventUser validateAndGetEventUserForRemoval(Long eventId, Long userId, String authenticatedEmail) {
        User authenticatedUser = findUserByEmail(authenticatedEmail);
        Event event = findEventById(eventId);
        validateCanManageEventUser(event, userId, authenticatedUser.getId());

        return eventUserRepository.findByEventIdAndUserId(eventId, userId)
                .orElseThrow(() -> new EventUserNotFoundException(eventId, userId));
    }

    @Override
    public EventUser updateParticipationStatus(Long eventId, Long userId, Long participationStatusId, String authenticatedEmail) {
        User authenticatedUser = findUserByEmail(authenticatedEmail);
        Event event = findEventById(eventId);
        validateCanManageEventUser(event, userId, authenticatedUser.getId());

        EventUser eventUser = eventUserRepository.findByEventIdAndUserId(eventId, userId)
                .orElseThrow(() -> new EventUserNotFoundException(eventId, userId));
        ParticipationStatus participationStatus = findParticipationStatusById(participationStatusId);
        eventUser.setParticipationStatus(participationStatus);
        return eventUser;
    }

    @Override
    public List<EventUser> getUsersByEvent(Long eventId, String authenticatedEmail) {
        User authenticatedUser = findUserByEmail(authenticatedEmail);
        Event event = findEventById(eventId);
        if (!event.getCreatedBy().getId().equals(authenticatedUser.getId())) {
            throw new UnauthorizedEventActionException();
        }
        List<EventUser> eventUsers = eventUserRepository.findByEventId(eventId);
        if (eventUsers.isEmpty()) {
            throw new NoEventsFoundException();
        }
        return eventUsers;
    }

    @Override
    public List<EventUser> getEventsByUser(Long userId, String authenticatedEmail) {
        User authenticatedUser = findUserByEmail(authenticatedEmail);
        if (!userId.equals(authenticatedUser.getId())) {
            throw new UnauthorizedEventActionException();
        }
        List<EventUser> eventUsers = eventUserRepository.findByUserId(userId);
        if (eventUsers.isEmpty()) {
            throw new NoEventsFoundException();
        }
        return eventUsers;
    }

    private void validateCanManageEventUser(Event event, Long targetUserId, Long authenticatedUserId) {
        boolean isCreator = event.getCreatedBy().getId().equals(authenticatedUserId);
        boolean isTargetUser = targetUserId.equals(authenticatedUserId);
        if (!isCreator && !isTargetUser) {
            throw new UnauthorizedEventActionException();
        }
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuário autenticado não encontrado."));
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    private Event findEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException(eventId));
    }

    private ParticipationStatus findParticipationStatusById(Long id) {
        return participationStatusRepository.findById(id)
                .orElseThrow(() -> new ParticipationStatusNotFoundException(id));
    }
}
