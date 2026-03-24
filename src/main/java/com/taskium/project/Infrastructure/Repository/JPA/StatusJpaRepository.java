package com.taskium.project.Infrastructure.Repository.JPA;

import com.taskium.project.Domain.Entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusJpaRepository extends JpaRepository<Status, Long> {
}

