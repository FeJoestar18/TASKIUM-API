package com.taskium.project.Application.DTO.Role;

import com.taskium.project.Application.DTO.Permission.PermissionResponseDTO;
import com.taskium.project.Domain.Entity.Role;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Builder
public class RoleResponseDTO {

    private final Long id;
    private final String name;
    private final String description;
    private final Set<PermissionResponseDTO> permissions;

    public static RoleResponseDTO from(Role role) {
        return RoleResponseDTO.builder()
                .id(role.getId())
                .name(role.getName().name())
                .description(role.getDescription())
                .permissions(role.getPermissions() != null
                        ? role.getPermissions().stream()
                            .map(PermissionResponseDTO::from)
                            .collect(Collectors.toSet())
                        : Set.of())
                .build();
    }
}

