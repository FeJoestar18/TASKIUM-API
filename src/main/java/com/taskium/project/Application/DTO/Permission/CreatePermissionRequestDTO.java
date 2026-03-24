package com.taskium.project.Application.DTO.Permission;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePermissionRequestDTO {

    @NotBlank(message = "Nome da permissão é obrigatório")
    @Size(max = 50, message = "Nome deve ter no máximo 50 caracteres")
    private String name;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 200, message = "Descrição deve ter no máximo 200 caracteres")
    private String description;
}

