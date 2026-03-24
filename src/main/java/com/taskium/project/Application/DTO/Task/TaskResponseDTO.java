package com.taskium.project.Application.DTO.Task;

import com.taskium.project.Domain.Entity.Task;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class TaskResponseDTO {

    private final Long id;
    private final String name;
    private final String description;
    private final String details;
    private final Integer hours;
    private final String taskStatus;
    private final String taskCategory;
    private final String createdByName;
    private final LocalDate dueDate;
    private final String priority;
    private final LocalDateTime createdAt;

    public static TaskResponseDTO from(Task task) {
        return TaskResponseDTO.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .details(task.getDetails())
                .hours(task.getHours())
                .taskStatus(task.getTaskStatus() != null ? task.getTaskStatus().getName() : null)
                .taskCategory(task.getTaskCategory() != null ? task.getTaskCategory().getName() : null)
                .createdByName(task.getCreatedBy() != null ? task.getCreatedBy().getName() : null)
                .dueDate(task.getDueDate())
                .priority(task.getPriority())
                .createdAt(task.getCreatedAt())
                .build();
    }
}

