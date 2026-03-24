package com.taskium.project.Application.UseCases.TaskStatus;

import com.taskium.project.Domain.Interfaces.Services.TaskStatus.ITaskStatusService;
import org.springframework.stereotype.Service;

@Service
public class DeleteTaskStatusByIdUseCase {

    private final ITaskStatusService taskStatusService;

    public DeleteTaskStatusByIdUseCase(ITaskStatusService taskStatusService) {
        this.taskStatusService = taskStatusService;
    }

    public void execute(Long id) {
        taskStatusService.deleteTaskStatusById(id);
    }
}

