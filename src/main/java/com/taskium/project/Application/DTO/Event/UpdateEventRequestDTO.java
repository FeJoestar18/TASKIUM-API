package com.taskium.project.Application.DTO.Event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateEventRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String name;

    private String description;

    @NotNull(message = "Data de início é obrigatória")
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Size(max = 100, message = "Local deve ter no máximo 100 caracteres")
    private String location;

    @NotNull(message = "Tipo de evento é obrigatório")
    private Long eventTypeId;
}
