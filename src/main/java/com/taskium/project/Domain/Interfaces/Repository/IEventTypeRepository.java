package com.taskium.project.Domain.Interfaces.Repository;

import com.taskium.project.Domain.Entity.EventType;

import java.util.List;
import java.util.Optional;

public interface IEventTypeRepository {
    EventType save(EventType eventType);
    Optional<EventType> findById(Long id);
    List<EventType> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
