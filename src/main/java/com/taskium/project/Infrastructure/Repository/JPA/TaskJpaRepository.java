package com.taskium.project.Infrastructure.Repository.JPA;

import com.taskium.project.Domain.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskJpaRepository extends JpaRepository<Task, Long> {
}

