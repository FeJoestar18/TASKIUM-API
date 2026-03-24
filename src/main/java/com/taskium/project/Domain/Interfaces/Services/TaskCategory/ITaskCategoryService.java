package com.taskium.project.Domain.Interfaces.Services.TaskCategory;

import com.taskium.project.Application.DTO.TaskCategory.TaskCategoryRequestDTO;
import com.taskium.project.Domain.Entity.TaskCategory;

import java.util.List;

public interface ITaskCategoryService {
    TaskCategory createTaskCategory(TaskCategoryRequestDTO dto);
    TaskCategory updateTaskCategoryById(Long id, TaskCategoryRequestDTO dto);
    void deleteTaskCategoryById(Long id);
    List<TaskCategory> getAllTaskCategories();
}

