package com.taskium.project.Application.UseCases.TaskCategory;

import com.taskium.project.Application.DTO.TaskCategory.TaskCategoryRequestDTO;
import com.taskium.project.Application.DTO.TaskCategory.TaskCategoryResponseDTO;
import com.taskium.project.Domain.Interfaces.Repository.ITaskCategoryRepository;
import com.taskium.project.Domain.Interfaces.Services.TaskCategory.ITaskCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateTaskCategoryUseCase {

    private final ITaskCategoryService taskCategoryService;
    private final ITaskCategoryRepository taskCategoryRepository;

    public CreateTaskCategoryUseCase(
            ITaskCategoryService taskCategoryService,
            ITaskCategoryRepository taskCategoryRepository
    ) {
        this.taskCategoryService = taskCategoryService;
        this.taskCategoryRepository = taskCategoryRepository;
    }

    @Transactional
    public TaskCategoryResponseDTO execute(TaskCategoryRequestDTO dto) {

        var category = taskCategoryService.createTaskCategory(dto);
        category = taskCategoryRepository.save(category);

        return TaskCategoryResponseDTO.from(category);
    }
}

