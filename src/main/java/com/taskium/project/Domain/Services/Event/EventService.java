package com.taskium.project.Domain.Services.Event;

import com.taskium.project.Domain.Common.Exceptions.Event.EventNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.Event.EventTypeNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.Event.NoEventsFoundException;
import com.taskium.project.Domain.Common.Exceptions.User.UserNotFoundException;
import com.taskium.project.Domain.Common.Validators.EventValidator;
import com.taskium.project.Domain.Entity.Event;
import com.taskium.project.Domain.Entity.EventType;
import com.taskium.project.Domain.Entity.User;
import com.taskium.project.Domain.Interfaces.Repository.IEventRepository;
import com.taskium.project.Domain.Interfaces.Repository.IEventTypeRepository;
import com.taskium.project.Domain.Interfaces.Repository.IUserRepository;
import com.taskium.project.Domain.Interfaces.Services.Event.IEventService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService implements IEventService {

    private final IEventRepository eventRepository;
    private final IEventTypeRepository eventTypeRepository;
    private final IUserRepository userRepository;
    private final EventValidator eventValidator;

    public EventService(
            IEventRepository eventRepository,
            IEventTypeRepository eventTypeRepository,
            IUserRepository userRepository,
            EventValidator eventValidator
    ) {
        this.eventRepository = eventRepository;
        this.eventTypeRepository = eventTypeRepository;
        this.userRepository = userRepository;
        this.eventValidator = eventValidator;
    }

    @Override
    public Event createEvent(String name, String description, LocalDateTime startDate, LocalDateTime endDate, String location, Long eventTypeId, String authenticatedEmail) {
        User authenticatedUser = findUserByEmail(authenticatedEmail);
        EventType eventType = findEventTypeById(eventTypeId);
        eventValidator.validateDateRange(startDate, endDate);

        Event event = Event.builder()
                .name(name)
                .description(description)
                .startDate(startDate)
                .endDate(endDate)
                .location(location)
                .createdBy(authenticatedUser)
                .eventType(eventType)
                .build();

        return event;
    }

    @Override
    public Event updateEvent(Long id, String name, String description, LocalDateTime startDate, LocalDateTime endDate, String location, Long eventTypeId, String authenticatedEmail) {
        User authenticatedUser = findUserByEmail(authenticatedEmail);
        Event event = findEventById(id);
        eventValidator.validateCreatorOwnership(event.getCreatedBy().getId(), authenticatedUser.getId());
        EventType eventType = findEventTypeById(eventTypeId);
        eventValidator.validateDateRange(startDate, endDate);

        event.setName(name);
        event.setDescription(description);
        event.setStartDate(startDate);
        event.setEndDate(endDate);
        event.setLocation(location);
        event.setEventType(eventType);

        return event;
    }

    @Override
    public void validateEventDeletion(Long id, String authenticatedEmail) {
        User authenticatedUser = findUserByEmail(authenticatedEmail);
        Event event = findEventById(id);
        eventValidator.validateCreatorOwnership(event.getCreatedBy().getId(), authenticatedUser.getId());
    }

    @Override
    public List<Event> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        if (events.isEmpty()) {
            throw new NoEventsFoundException();
        }
        return events;
    }

    @Override
    public Event getEventById(Long id) {
        return findEventById(id);
    }

    private Event findEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    private EventType findEventTypeById(Long id) {
        return eventTypeRepository.findById(id)
                .orElseThrow(() -> new EventTypeNotFoundException(id));
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuário autenticado não encontrado."));
    }
}
