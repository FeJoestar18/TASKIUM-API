package com.taskium.project.Application.DTO.Task;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequestDTO {

    @NotBlank(message = "Nome da tarefa é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String name;

    private String description;

    private String details;

    private Integer hours;

    @NotNull(message = "ID do status é obrigatório")
    private Long taskStatusId;

    @NotNull(message = "ID da categoria é obrigatório")
    private Long taskCategoryId;

    @NotNull(message = "ID do criador é obrigatório")
    private Long createdById;

    private LocalDate dueDate;

    @Size(max = 20, message = "Prioridade deve ter no máximo 20 caracteres")
    private String priority;
}

