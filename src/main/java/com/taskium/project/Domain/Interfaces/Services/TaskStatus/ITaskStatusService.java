package com.taskium.project.Domain.Interfaces.Services.TaskStatus;

import com.taskium.project.Application.DTO.TaskStatus.TaskStatusRequestDTO;
import com.taskium.project.Domain.Entity.TaskStatus;

import java.util.List;

public interface ITaskStatusService {
    TaskStatus createTaskStatus(TaskStatusRequestDTO dto);
    TaskStatus updateTaskStatusById(Long id, TaskStatusRequestDTO dto);
    void deleteTaskStatusById(Long id);
    List<TaskStatus> getAllTaskStatuses();
}

