package com.taskium.project.Application.DTO.TaskCategory;

import com.taskium.project.Domain.Entity.TaskCategory;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TaskCategoryResponseDTO {

    private final Long id;
    private final String name;
    private final String description;
    private final String color;
    private final String icon;

    public static TaskCategoryResponseDTO from(TaskCategory category) {
        return TaskCategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .color(category.getColor())
                .icon(category.getIcon())
                .build();
    }
}

