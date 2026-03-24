package com.taskium.project.Domain.Services.TaskCategory;

import com.taskium.project.Application.DTO.TaskCategory.TaskCategoryRequestDTO;
import com.taskium.project.Domain.Common.Exceptions.TaskCategory.TaskCategoryNotFoundException;
import com.taskium.project.Domain.Entity.TaskCategory;
import com.taskium.project.Domain.Interfaces.Repository.ITaskCategoryRepository;
import com.taskium.project.Domain.Interfaces.Services.TaskCategory.ITaskCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskCategoryService implements ITaskCategoryService {

    private final ITaskCategoryRepository taskCategoryRepository;

    public TaskCategoryService(ITaskCategoryRepository taskCategoryRepository) {
        this.taskCategoryRepository = taskCategoryRepository;
    }

    @Override
    public TaskCategory createTaskCategory(TaskCategoryRequestDTO dto) {
        return TaskCategory.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .color(dto.getColor())
                .icon(dto.getIcon())
                .build();
    }

    @Override
    public TaskCategory updateTaskCategoryById(Long id, TaskCategoryRequestDTO dto) {

        TaskCategory category = taskCategoryRepository.findById(id)
                .orElseThrow(() -> new TaskCategoryNotFoundException(id));

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setColor(dto.getColor());
        category.setIcon(dto.getIcon());

        taskCategoryRepository.save(category);

        return category;
    }

    @Override
    public void deleteTaskCategoryById(Long id) {

        if (!taskCategoryRepository.existsById(id)) {
            throw new TaskCategoryNotFoundException(id);
        }

        taskCategoryRepository.deleteById(id);
    }

    @Override
    public List<TaskCategory> getAllTaskCategories() {
        return taskCategoryRepository.findAll();
    }
}

