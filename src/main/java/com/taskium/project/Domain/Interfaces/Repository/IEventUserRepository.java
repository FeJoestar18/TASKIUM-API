package com.taskium.project.Domain.Interfaces.Repository;

import com.taskium.project.Domain.Entity.EventUser;

import java.util.List;
import java.util.Optional;

public interface IEventUserRepository {
    EventUser save(EventUser eventUser);
    Optional<EventUser> findByEventIdAndUserId(Long eventId, Long userId);
    List<EventUser> findByEventId(Long eventId);
    List<EventUser> findByUserId(Long userId);
    boolean existsByUserIdAndEventId(Long userId, Long eventId);
    void delete(EventUser eventUser);
}
