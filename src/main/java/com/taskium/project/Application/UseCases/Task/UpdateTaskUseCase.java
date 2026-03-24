package com.taskium.project.Application.UseCases.Task;

import com.taskium.project.Application.DTO.Task.TaskRequestDTO;
import com.taskium.project.Application.DTO.Task.TaskResponseDTO;
import com.taskium.project.Domain.Common.Exceptions.TaskCategory.TaskCategoryNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.TaskStatus.TaskStatusNotFoundException;
import com.taskium.project.Domain.Interfaces.Repository.ITaskCategoryRepository;
import com.taskium.project.Domain.Interfaces.Repository.ITaskStatusRepository;
import com.taskium.project.Domain.Interfaces.Services.Task.ITaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateTaskUseCase {

    private final ITaskService taskService;
    private final ITaskStatusRepository taskStatusRepository;
    private final ITaskCategoryRepository taskCategoryRepository;

    public UpdateTaskUseCase(
            ITaskService taskService,
            ITaskStatusRepository taskStatusRepository,
            ITaskCategoryRepository taskCategoryRepository
    ) {
        this.taskService = taskService;
        this.taskStatusRepository = taskStatusRepository;
        this.taskCategoryRepository = taskCategoryRepository;
    }

    @Transactional
    public TaskResponseDTO execute(Long id, TaskRequestDTO dto) {

        var status = taskStatusRepository.findById(dto.getTaskStatusId())
                .orElseThrow(() -> new TaskStatusNotFoundException(dto.getTaskStatusId()));

        var category = taskCategoryRepository.findById(dto.getTaskCategoryId())
                .orElseThrow(() -> new TaskCategoryNotFoundException(dto.getTaskCategoryId()));

        var task = taskService.updateTaskById(id, dto, status, category);

        return TaskResponseDTO.from(task);
    }
}

