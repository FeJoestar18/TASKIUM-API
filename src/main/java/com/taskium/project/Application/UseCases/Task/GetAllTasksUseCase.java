package com.taskium.project.Application.UseCases.Task;

import com.taskium.project.Application.DTO.Task.TaskResponseDTO;
import com.taskium.project.Domain.Common.Exceptions.Task.NoTasksFoundException;
import com.taskium.project.Domain.Interfaces.Services.Task.ITaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllTasksUseCase {

    private final ITaskService taskService;

    public GetAllTasksUseCase(ITaskService taskService) {
        this.taskService = taskService;
    }

    public List<TaskResponseDTO> execute() {

        var tasks = taskService.getAllTasks();

        if (tasks.isEmpty()) {
            throw new NoTasksFoundException();
        }

        return tasks.stream()
                .map(TaskResponseDTO::from)
                .toList();
    }
}

