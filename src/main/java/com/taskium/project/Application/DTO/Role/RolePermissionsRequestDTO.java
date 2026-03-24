package com.taskium.project.Application.DTO.Role;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolePermissionsRequestDTO {

    @NotEmpty(message = "Lista de permissionIds não pode ser vazia")
    private Set<Long> permissionIds;
}

