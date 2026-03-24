package com.taskium.project.Domain.Interfaces.Repository;

import com.taskium.project.Domain.Entity.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskRepository {
    Task save(Task task);
    Optional<Task> findById(Long id);
    List<Task> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}

