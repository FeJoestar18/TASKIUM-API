package com.taskium.project.Infrastructure.Repository.Adapters;

import com.taskium.project.Domain.Entity.Event;
import com.taskium.project.Domain.Interfaces.Repository.IEventRepository;
import com.taskium.project.Infrastructure.Repository.JPA.EventJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EventRepositoryImpl implements IEventRepository {

    private final EventJpaRepository eventJpaRepository;

    public EventRepositoryImpl(EventJpaRepository eventJpaRepository) {
        this.eventJpaRepository = eventJpaRepository;
    }

    @Override
    public Event save(Event event) {
        return eventJpaRepository.save(event);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventJpaRepository.findById(id);
    }

    @Override
    public List<Event> findAll() {
        return eventJpaRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        eventJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return eventJpaRepository.existsById(id);
    }
}
