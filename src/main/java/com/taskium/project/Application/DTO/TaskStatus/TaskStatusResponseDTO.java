package com.taskium.project.Application.DTO.TaskStatus;

import com.taskium.project.Domain.Entity.TaskStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TaskStatusResponseDTO {

    private final Long id;
    private final String name;
    private final String color;

    public static TaskStatusResponseDTO from(TaskStatus status) {
        return TaskStatusResponseDTO.builder()
                .id(status.getId())
                .name(status.getName())
                .color(status.getColor())
                .build();
    }
}

