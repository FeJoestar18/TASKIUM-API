package com.taskium.project.Application.UseCases.TaskStatus;

import com.taskium.project.Application.DTO.TaskStatus.TaskStatusResponseDTO;
import com.taskium.project.Domain.Common.Exceptions.TaskStatus.NoTaskStatusesFoundException;
import com.taskium.project.Domain.Interfaces.Services.TaskStatus.ITaskStatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllTaskStatusesUseCase {

    private final ITaskStatusService taskStatusService;

    public GetAllTaskStatusesUseCase(ITaskStatusService taskStatusService) {
        this.taskStatusService = taskStatusService;
    }

    public List<TaskStatusResponseDTO> execute() {

        var statuses = taskStatusService.getAllTaskStatuses();

        if (statuses.isEmpty()) {
            throw new NoTaskStatusesFoundException();
        }

        return statuses.stream()
                .map(TaskStatusResponseDTO::from)
                .toList();
    }
}

