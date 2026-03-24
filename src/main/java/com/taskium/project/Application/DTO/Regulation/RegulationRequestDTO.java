package com.taskium.project.Application.DTO.Regulation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegulationRequestDTO {

    @NotBlank(message = "Título do regulamento é obrigatório")
    @Size(min = 3, max = 150, message = "Título deve ter entre 3 e 150 caracteres")
    private String title;

    @NotBlank(message = "Conteúdo do regulamento é obrigatório")
    private String content;

    @NotNull(message = "Campo de ativo é obrigatório")
    private Boolean isActive;
}
