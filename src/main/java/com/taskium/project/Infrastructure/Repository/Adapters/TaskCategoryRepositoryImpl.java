package com.taskium.project.Infrastructure.Repository.Adapters;

import com.taskium.project.Domain.Entity.TaskCategory;
import com.taskium.project.Domain.Interfaces.Repository.ITaskCategoryRepository;
import com.taskium.project.Infrastructure.Repository.JPA.TaskCategoryJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskCategoryRepositoryImpl implements ITaskCategoryRepository {

    private final TaskCategoryJpaRepository jpaRepository;

    public TaskCategoryRepositoryImpl(TaskCategoryJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public TaskCategory save(TaskCategory taskCategory) {
        return jpaRepository.save(taskCategory);
    }

    @Override
    public Optional<TaskCategory> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<TaskCategory> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByName(name);
    }
}

