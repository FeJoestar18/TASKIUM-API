package com.taskium.project.Infrastructure.Repository.JPA;

import com.taskium.project.Domain.Entity.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskCategoryJpaRepository extends JpaRepository<TaskCategory, Long> {
    boolean existsByName(String name);
}

