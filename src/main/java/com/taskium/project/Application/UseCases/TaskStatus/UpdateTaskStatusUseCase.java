package com.taskium.project.Application.UseCases.TaskStatus;

import com.taskium.project.Application.DTO.TaskStatus.TaskStatusRequestDTO;
import com.taskium.project.Application.DTO.TaskStatus.TaskStatusResponseDTO;
import com.taskium.project.Domain.Interfaces.Services.TaskStatus.ITaskStatusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateTaskStatusUseCase {

    private final ITaskStatusService taskStatusService;

    public UpdateTaskStatusUseCase(ITaskStatusService taskStatusService) {
        this.taskStatusService = taskStatusService;
    }

    @Transactional
    public TaskStatusResponseDTO execute(Long id, TaskStatusRequestDTO dto) {

        var status = taskStatusService.updateTaskStatusById(id, dto);

        return TaskStatusResponseDTO.from(status);
    }
}

