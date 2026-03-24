package com.taskium.project.Application.DTO.TaskCategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskCategoryRequestDTO {

    @NotBlank(message = "Nome da categoria é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String name;

    @Size(max = 200, message = "Descrição deve ter no máximo 200 caracteres")
    private String description;

    @Size(max = 7, message = "Cor deve ter no máximo 7 caracteres")
    private String color;

    @Size(max = 100, message = "Ícone deve ter no máximo 100 caracteres")
    private String icon;
}

