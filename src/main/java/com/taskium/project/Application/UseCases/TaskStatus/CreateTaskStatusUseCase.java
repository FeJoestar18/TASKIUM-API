package com.taskium.project.Application.UseCases.TaskStatus;

import com.taskium.project.Application.DTO.TaskStatus.TaskStatusRequestDTO;
import com.taskium.project.Application.DTO.TaskStatus.TaskStatusResponseDTO;
import com.taskium.project.Domain.Interfaces.Repository.ITaskStatusRepository;
import com.taskium.project.Domain.Interfaces.Services.TaskStatus.ITaskStatusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateTaskStatusUseCase {

    private final ITaskStatusService taskStatusService;
    private final ITaskStatusRepository taskStatusRepository;

    public CreateTaskStatusUseCase(
            ITaskStatusService taskStatusService,
            ITaskStatusRepository taskStatusRepository
    ) {
        this.taskStatusService = taskStatusService;
        this.taskStatusRepository = taskStatusRepository;
    }

    @Transactional
    public TaskStatusResponseDTO execute(TaskStatusRequestDTO dto) {

        var status = taskStatusService.createTaskStatus(dto);
        status = taskStatusRepository.save(status);

        return TaskStatusResponseDTO.from(status);
    }
}

