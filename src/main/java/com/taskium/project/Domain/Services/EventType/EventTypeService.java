package com.taskium.project.Domain.Services.EventType;

import com.taskium.project.Application.DTO.EventType.EventTypeRequestDTO;
import com.taskium.project.Domain.Common.Exceptions.Event.EventTypeNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.Event.NoEventTypesFoundException;
import com.taskium.project.Domain.Entity.EventType;
import com.taskium.project.Domain.Interfaces.Repository.IEventTypeRepository;
import com.taskium.project.Domain.Interfaces.Services.EventType.IEventTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventTypeService implements IEventTypeService {

    private final IEventTypeRepository eventTypeRepository;

    public EventTypeService(IEventTypeRepository eventTypeRepository) {
        this.eventTypeRepository = eventTypeRepository;
    }

    @Override
    public EventType create(EventTypeRequestDTO dto) {
        return eventTypeRepository.save(
                EventType.builder()
                        .name(dto.getName())
                        .description(dto.getDescription())
                        .build()
        );
    }

    @Override
    public EventType update(Long id, EventTypeRequestDTO dto) {
        EventType eventType = eventTypeRepository.findById(id)
                .orElseThrow(() -> new EventTypeNotFoundException(id));
        eventType.setName(dto.getName());
        eventType.setDescription(dto.getDescription());
        return eventTypeRepository.save(eventType);
    }

    @Override
    public void delete(Long id) {
        if (!eventTypeRepository.existsById(id)) {
            throw new EventTypeNotFoundException(id);
        }
        eventTypeRepository.deleteById(id);
    }

    @Override
    public List<EventType> getAll() {
        List<EventType> eventTypes = eventTypeRepository.findAll();
        if (eventTypes.isEmpty()) {
            throw new NoEventTypesFoundException();
        }
        return eventTypes;
    }
}
