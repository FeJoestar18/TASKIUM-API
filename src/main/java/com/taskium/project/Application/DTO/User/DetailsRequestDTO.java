package com.taskium.project.Application.DTO.User;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailsRequestDTO {

    @Past(message = "Data de nascimento deve ser no passado")
    private LocalDate birthday;

    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone reserva inválido, formato: (00) 00000-0000")
    private String reservedPhoneNumber;

    @Email(message = "Email reserva inválido")
    private String reservedEmail;

    @NotNull(message = "Role é obrigatória")
    private Long roleId;

    @NotNull(message = "Status de participação é obrigatório")
    private Long participantStatusId;
}

