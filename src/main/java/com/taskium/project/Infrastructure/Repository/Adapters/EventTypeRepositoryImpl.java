package com.taskium.project.Infrastructure.Repository.Adapters;

import com.taskium.project.Domain.Entity.EventType;
import com.taskium.project.Domain.Interfaces.Repository.IEventTypeRepository;
import com.taskium.project.Infrastructure.Repository.JPA.EventTypeJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EventTypeRepositoryImpl implements IEventTypeRepository {

    private final EventTypeJpaRepository eventTypeJpaRepository;

    public EventTypeRepositoryImpl(EventTypeJpaRepository eventTypeJpaRepository) {
        this.eventTypeJpaRepository = eventTypeJpaRepository;
    }

    @Override
    public EventType save(EventType eventType) {
        return eventTypeJpaRepository.save(eventType);
    }

    @Override
    public Optional<EventType> findById(Long id) {
        return eventTypeJpaRepository.findById(id);
    }

    @Override
    public List<EventType> findAll() {
        return eventTypeJpaRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        eventTypeJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return eventTypeJpaRepository.existsById(id);
    }
}
