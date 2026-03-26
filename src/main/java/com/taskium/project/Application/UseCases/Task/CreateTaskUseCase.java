package com.taskium.project.Application.UseCases.Task;

import com.taskium.project.Application.DTO.Task.TaskRequestDTO;
import com.taskium.project.Application.DTO.Task.TaskResponseDTO;
import com.taskium.project.Domain.Common.Exceptions.TaskCategory.TaskCategoryNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.TaskStatus.TaskStatusNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.User.UserNotFoundException;
import com.taskium.project.Domain.Interfaces.Repository.ITaskCategoryRepository;
import com.taskium.project.Domain.Interfaces.Repository.ITaskRepository;
import com.taskium.project.Domain.Interfaces.Repository.ITaskStatusRepository;
import com.taskium.project.Domain.Interfaces.Repository.IUserRepository;
import com.taskium.project.Domain.Interfaces.Services.Task.ITaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateTaskUseCase {

    private final ITaskService taskService;
    private final ITaskRepository taskRepository;
    private final ITaskStatusRepository taskStatusRepository;
    private final ITaskCategoryRepository taskCategoryRepository;
    private final IUserRepository userRepository;

    public CreateTaskUseCase(
            ITaskService taskService,
            ITaskRepository taskRepository,
            ITaskStatusRepository taskStatusRepository,
            ITaskCategoryRepository taskCategoryRepository,
            IUserRepository userRepository
    ) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
        this.taskStatusRepository = taskStatusRepository;
        this.taskCategoryRepository = taskCategoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public TaskResponseDTO execute(TaskRequestDTO dto) {

        var status = taskStatusRepository.findById(dto.getTaskStatusId())
                .orElseThrow(() -> new TaskStatusNotFoundException(dto.getTaskStatusId()));

        var category = taskCategoryRepository.findById(dto.getTaskCategoryId())
                .orElseThrow(() -> new TaskCategoryNotFoundException(dto.getTaskCategoryId()));

        var createdBy = userRepository.findById(dto.getCreatedById())
                .orElseThrow(() -> new UserNotFoundException(dto.getCreatedById()));

        var task = taskService.createTask(
                dto.getName(), 
                dto.getDescription(), 
                dto.getDetails(), 
                dto.getHours(), 
                dto.getDueDate(), 
                dto.getPriority(), 
                status, 
                category, 
                createdBy
        );

        task = taskRepository.save(task);

        return TaskResponseDTO.from(task);
    }
}

