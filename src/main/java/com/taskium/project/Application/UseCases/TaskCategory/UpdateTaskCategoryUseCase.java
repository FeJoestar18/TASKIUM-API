package com.taskium.project.Application.UseCases.TaskCategory;

import com.taskium.project.Application.DTO.TaskCategory.TaskCategoryRequestDTO;
import com.taskium.project.Application.DTO.TaskCategory.TaskCategoryResponseDTO;
import com.taskium.project.Domain.Interfaces.Services.TaskCategory.ITaskCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateTaskCategoryUseCase {

    private final ITaskCategoryService taskCategoryService;

    public UpdateTaskCategoryUseCase(ITaskCategoryService taskCategoryService) {
        this.taskCategoryService = taskCategoryService;
    }

    @Transactional
    public TaskCategoryResponseDTO execute(Long id, TaskCategoryRequestDTO dto) {

        var category = taskCategoryService.updateTaskCategoryById(id, dto);

        return TaskCategoryResponseDTO.from(category);
    }
}

