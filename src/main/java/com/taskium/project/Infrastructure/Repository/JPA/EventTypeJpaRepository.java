package com.taskium.project.Infrastructure.Repository.JPA;

import com.taskium.project.Domain.Entity.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTypeJpaRepository extends JpaRepository<EventType, Long> {
}
