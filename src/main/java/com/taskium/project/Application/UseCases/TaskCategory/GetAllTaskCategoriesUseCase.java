package com.taskium.project.Application.UseCases.TaskCategory;

import com.taskium.project.Application.DTO.TaskCategory.TaskCategoryResponseDTO;
import com.taskium.project.Domain.Common.Exceptions.TaskCategory.NoTaskCategoriesFoundException;
import com.taskium.project.Domain.Interfaces.Services.TaskCategory.ITaskCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllTaskCategoriesUseCase {

    private final ITaskCategoryService taskCategoryService;

    public GetAllTaskCategoriesUseCase(ITaskCategoryService taskCategoryService) {
        this.taskCategoryService = taskCategoryService;
    }

    public List<TaskCategoryResponseDTO> execute() {

        var categories = taskCategoryService.getAllTaskCategories();

        if (categories.isEmpty()) {
            throw new NoTaskCategoriesFoundException();
        }

        return categories.stream()
                .map(TaskCategoryResponseDTO::from)
                .toList();
    }
}

