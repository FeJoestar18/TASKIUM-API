package com.taskium.project.Application.UseCases.Task;

import com.taskium.project.Domain.Interfaces.Repository.ITaskRepository;
import com.taskium.project.Domain.Interfaces.Services.Task.ITaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteTaskByIdUseCase {

    private final ITaskService taskService;
    private final ITaskRepository taskRepository;

    public DeleteTaskByIdUseCase(ITaskService taskService, ITaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    @Transactional
    public void execute(Long id) {
        taskService.deleteTaskById(id);
        taskRepository.deleteById(id);
    }
}

