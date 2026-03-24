package com.taskium.project.Application.DTO.Comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCommentRequestDTO {

    @NotBlank(message = "Descrição do comentário é obrigatória")
    @Size(min = 1, max = 5000, message = "Descrição deve ter entre 1 e 5000 caracteres")
    private String description;
}

