package com.taskium.project.Domain.Interfaces.Services.Task;

import com.taskium.project.Application.DTO.Task.TaskRequestDTO;
import com.taskium.project.Domain.Entity.Task;
import com.taskium.project.Domain.Entity.TaskCategory;
import com.taskium.project.Domain.Entity.TaskStatus;
import com.taskium.project.Domain.Entity.User;

import java.util.List;

public interface ITaskService {
    Task createTask(TaskRequestDTO dto, TaskStatus status, TaskCategory category, User createdBy);
    Task updateTaskById(Long id, TaskRequestDTO dto, TaskStatus status, TaskCategory category);
    void deleteTaskById(Long id);
    List<Task> getAllTasks();
}

