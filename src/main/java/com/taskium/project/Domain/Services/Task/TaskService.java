package com.taskium.project.Domain.Services.Task;

import com.taskium.project.Domain.Common.Exceptions.Task.TaskNotFoundException;
import com.taskium.project.Domain.Entity.Task;
import com.taskium.project.Domain.Entity.TaskCategory;
import com.taskium.project.Domain.Entity.TaskStatus;
import com.taskium.project.Domain.Entity.User;
import com.taskium.project.Domain.Interfaces.Repository.ITaskRepository;
import com.taskium.project.Domain.Interfaces.Services.Task.ITaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService implements ITaskService {

    private final ITaskRepository taskRepository;

    public TaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(String name, String description, String details, Integer hours, LocalDate dueDate, String priority, TaskStatus status, TaskCategory category, User createdBy) {
        return Task.builder()
                .name(name)
                .description(description)
                .details(details)
                .hours(hours)
                .taskStatus(status)
                .taskCategory(category)
                .createdBy(createdBy)
                .dueDate(dueDate)
                .priority(priority)
                .build();
    }

    @Override
    public Task updateTaskById(Long id, String name, String description, String details, Integer hours, LocalDate dueDate, String priority, TaskStatus status, TaskCategory category) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.setName(name);
        task.setDescription(description);
        task.setDetails(details);
        task.setHours(hours);
        task.setTaskStatus(status);
        task.setTaskCategory(category);
        task.setDueDate(dueDate);
        task.setPriority(priority);

        return task;
    }

    @Override
    public void deleteTaskById(Long id) {

        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}

