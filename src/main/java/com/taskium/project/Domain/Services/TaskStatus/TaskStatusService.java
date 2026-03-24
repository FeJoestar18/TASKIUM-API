package com.taskium.project.Domain.Services.TaskStatus;

import com.taskium.project.Application.DTO.TaskStatus.TaskStatusRequestDTO;
import com.taskium.project.Domain.Common.Exceptions.TaskStatus.TaskStatusNotFoundException;
import com.taskium.project.Domain.Entity.TaskStatus;
import com.taskium.project.Domain.Interfaces.Repository.ITaskStatusRepository;
import com.taskium.project.Domain.Interfaces.Services.TaskStatus.ITaskStatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskStatusService implements ITaskStatusService {

    private final ITaskStatusRepository taskStatusRepository;

    public TaskStatusService(ITaskStatusRepository taskStatusRepository) {
        this.taskStatusRepository = taskStatusRepository;
    }

    @Override
    public TaskStatus createTaskStatus(TaskStatusRequestDTO dto) {
        return TaskStatus.builder()
                .name(dto.getName())
                .color(dto.getColor())
                .build();
    }

    @Override
    public TaskStatus updateTaskStatusById(Long id, TaskStatusRequestDTO dto) {

        TaskStatus status = taskStatusRepository.findById(id)
                .orElseThrow(() -> new TaskStatusNotFoundException(id));

        status.setName(dto.getName());
        status.setColor(dto.getColor());

        taskStatusRepository.save(status);

        return status;
    }

    @Override
    public void deleteTaskStatusById(Long id) {

        if (!taskStatusRepository.existsById(id)) {
            throw new TaskStatusNotFoundException(id);
        }

        taskStatusRepository.deleteById(id);
    }

    @Override
    public List<TaskStatus> getAllTaskStatuses() {
        return taskStatusRepository.findAll();
    }
}

