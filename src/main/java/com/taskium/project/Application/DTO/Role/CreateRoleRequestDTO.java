package com.taskium.project.Application.DTO.Role;

import com.taskium.project.Domain.Enums.RoleName;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRoleRequestDTO {

    @NotNull(message = "Nome da role é obrigatório")
    private RoleName name;

    private String description;

    private Set<Long> permissionIds;
}

