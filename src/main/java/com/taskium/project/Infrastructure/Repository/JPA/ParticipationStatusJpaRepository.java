package com.taskium.project.Infrastructure.Repository.JPA;

import com.taskium.project.Domain.Entity.ParticipationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationStatusJpaRepository extends JpaRepository<ParticipationStatus, Long> {
}
