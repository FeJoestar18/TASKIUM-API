package com.taskium.project.Domain.Interfaces.Repository;

import com.taskium.project.Domain.Entity.TaskCategory;

import java.util.List;
import java.util.Optional;

public interface ITaskCategoryRepository {
    TaskCategory save(TaskCategory taskCategory);
    Optional<TaskCategory> findById(Long id);
    List<TaskCategory> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    boolean existsByName(String name);
}

