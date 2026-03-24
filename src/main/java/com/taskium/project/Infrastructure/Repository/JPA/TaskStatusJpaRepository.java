package com.taskium.project.Infrastructure.Repository.JPA;

import com.taskium.project.Domain.Entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusJpaRepository extends JpaRepository<TaskStatus, Long> {
    boolean existsByName(String name);
}

