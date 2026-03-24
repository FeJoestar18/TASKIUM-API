package com.taskium.project.Infrastructure.Repository.Adapters;

import com.taskium.project.Domain.Entity.EventUser;
import com.taskium.project.Domain.Interfaces.Repository.IEventUserRepository;
import com.taskium.project.Infrastructure.Repository.JPA.EventUserJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EventUserRepositoryImpl implements IEventUserRepository {

    private final EventUserJpaRepository eventUserJpaRepository;

    public EventUserRepositoryImpl(EventUserJpaRepository eventUserJpaRepository) {
        this.eventUserJpaRepository = eventUserJpaRepository;
    }

    @Override
    public EventUser save(EventUser eventUser) {
        return eventUserJpaRepository.save(eventUser);
    }

    @Override
    public Optional<EventUser> findByEventIdAndUserId(Long eventId, Long userId) {
        return eventUserJpaRepository.findByEvent_IdAndUser_Id(eventId, userId);
    }

    @Override
    public List<EventUser> findByEventId(Long eventId) {
        return eventUserJpaRepository.findByEvent_Id(eventId);
    }

    @Override
    public List<EventUser> findByUserId(Long userId) {
        return eventUserJpaRepository.findByUser_Id(userId);
    }

    @Override
    public boolean existsByUserIdAndEventId(Long userId, Long eventId) {
        return eventUserJpaRepository.existsByUser_IdAndEvent_Id(userId, eventId);
    }

    @Override
    public void delete(EventUser eventUser) {
        eventUserJpaRepository.delete(eventUser);
    }
}
