package com.taskium.project.Application.UseCases.Task;

import com.taskium.project.Domain.Interfaces.Services.Task.ITaskService;
import org.springframework.stereotype.Service;

@Service
public class DeleteTaskByIdUseCase {

    private final ITaskService taskService;

    public DeleteTaskByIdUseCase(ITaskService taskService) {
        this.taskService = taskService;
    }

    public void execute(Long id) {
        taskService.deleteTaskById(id);
    }
}

