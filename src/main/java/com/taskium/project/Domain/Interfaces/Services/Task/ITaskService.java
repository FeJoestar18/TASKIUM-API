package com.taskium.project.Domain.Interfaces.Services.Task;

import com.taskium.project.Domain.Entity.Task;
import com.taskium.project.Domain.Entity.TaskCategory;
import com.taskium.project.Domain.Entity.TaskStatus;
import com.taskium.project.Domain.Entity.User;

import java.time.LocalDate;
import java.util.List;

public interface ITaskService {
    Task createTask(String name, String description, String details, Integer hours, LocalDate dueDate, String priority, TaskStatus status, TaskCategory category, User createdBy);
    Task updateTaskById(Long id, String name, String description, String details, Integer hours, LocalDate dueDate, String priority, TaskStatus status, TaskCategory category);
    void deleteTaskById(Long id);
    List<Task> getAllTasks();
}

