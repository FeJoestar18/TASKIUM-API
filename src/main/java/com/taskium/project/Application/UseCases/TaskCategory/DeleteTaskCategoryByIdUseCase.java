package com.taskium.project.Application.UseCases.TaskCategory;

import com.taskium.project.Domain.Interfaces.Services.TaskCategory.ITaskCategoryService;
import org.springframework.stereotype.Service;

@Service
public class DeleteTaskCategoryByIdUseCase {

    private final ITaskCategoryService taskCategoryService;

    public DeleteTaskCategoryByIdUseCase(ITaskCategoryService taskCategoryService) {
        this.taskCategoryService = taskCategoryService;
    }

    public void execute(Long id) {
        taskCategoryService.deleteTaskCategoryById(id);
    }
}

