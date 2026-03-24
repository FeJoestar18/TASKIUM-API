package com.taskium.project.Application.DTO.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskStatusRequestDTO {

    @NotBlank(message = "Nome do status é obrigatório")
    @Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 50 caracteres")
    private String name;

    @Size(max = 7, message = "Cor deve ter no máximo 7 caracteres")
    private String color;
}

