package com.taskium.project.Application.DTO.Permission;

import com.taskium.project.Domain.Entity.Permission;
import lombok.*;

@Getter
@Builder
public class PermissionResponseDTO {

    private final Long id;
    private final String name;
    private final String description;

    public static PermissionResponseDTO from(Permission permission) {
        return PermissionResponseDTO.builder()
                .id(permission.getId())
                .name(permission.getName())
                .description(permission.getDescription())
                .build();
    }
}

