package com.taskium.project.Infrastructure.Repository.Adapters;

import com.taskium.project.Domain.Entity.TaskStatus;
import com.taskium.project.Domain.Interfaces.Repository.ITaskStatusRepository;
import com.taskium.project.Infrastructure.Repository.JPA.TaskStatusJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskStatusRepositoryImpl implements ITaskStatusRepository {

    private final TaskStatusJpaRepository jpaRepository;

    public TaskStatusRepositoryImpl(TaskStatusJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public TaskStatus save(TaskStatus taskStatus) {
        return jpaRepository.save(taskStatus);
    }

    @Override
    public Optional<TaskStatus> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<TaskStatus> findAll() {
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

