package com.taskium.project.Domain.Interfaces.Repository;

import com.taskium.project.Domain.Entity.TaskStatus;

import java.util.List;
import java.util.Optional;

public interface ITaskStatusRepository {
    TaskStatus save(TaskStatus taskStatus);
    Optional<TaskStatus> findById(Long id);
    List<TaskStatus> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    boolean existsByName(String name);
}

