package com.taskium.project.Infrastructure.Repository.JPA;

import com.taskium.project.Domain.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventJpaRepository extends JpaRepository<Event, Long> {
}
