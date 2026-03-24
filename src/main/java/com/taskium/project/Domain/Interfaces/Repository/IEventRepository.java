package com.taskium.project.Domain.Interfaces.Repository;

import com.taskium.project.Domain.Entity.Event;

import java.util.List;
import java.util.Optional;

public interface IEventRepository {
    Event save(Event event);
    Optional<Event> findById(Long id);
    List<Event> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
