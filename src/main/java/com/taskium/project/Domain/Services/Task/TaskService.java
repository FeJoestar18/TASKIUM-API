package com.taskium.project.Domain.Services.Task;

import com.taskium.project.Application.DTO.Task.TaskRequestDTO;
import com.taskium.project.Domain.Common.Exceptions.Task.TaskNotFoundException;
import com.taskium.project.Domain.Entity.Task;
import com.taskium.project.Domain.Entity.TaskCategory;
import com.taskium.project.Domain.Entity.TaskStatus;
import com.taskium.project.Domain.Entity.User;
import com.taskium.project.Domain.Interfaces.Repository.ITaskRepository;
import com.taskium.project.Domain.Interfaces.Services.Task.ITaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements ITaskService {

    private final ITaskRepository taskRepository;

    public TaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(TaskRequestDTO dto, TaskStatus status, TaskCategory category, User createdBy) {
        return Task.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .details(dto.getDetails())
                .hours(dto.getHours())
                .taskStatus(status)
                .taskCategory(category)
                .createdBy(createdBy)
                .dueDate(dto.getDueDate())
                .priority(dto.getPriority())
                .build();
    }

    @Override
    public Task updateTaskById(Long id, TaskRequestDTO dto, TaskStatus status, TaskCategory category) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.setName(dto.getName());
        task.setDescription(dto.getDescription());
        task.setDetails(dto.getDetails());
        task.setHours(dto.getHours());
        task.setTaskStatus(status);
        task.setTaskCategory(category);
        task.setDueDate(dto.getDueDate());
        task.setPriority(dto.getPriority());

        taskRepository.save(task);

        return task;
    }

    @Override
    public void deleteTaskById(Long id) {

        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }

        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}

