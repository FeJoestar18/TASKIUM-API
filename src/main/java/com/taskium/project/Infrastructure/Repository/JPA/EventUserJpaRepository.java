package com.taskium.project.Infrastructure.Repository.JPA;

import com.taskium.project.Domain.Entity.EventUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventUserJpaRepository extends JpaRepository<EventUser, Long> {
    Optional<EventUser> findByEvent_IdAndUser_Id(Long eventId, Long userId);
    List<EventUser> findByEvent_Id(Long eventId);
    List<EventUser> findByUser_Id(Long userId);
    boolean existsByUser_IdAndEvent_Id(Long userId, Long eventId);
}
