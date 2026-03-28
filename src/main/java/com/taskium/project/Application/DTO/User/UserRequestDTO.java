package com.taskium.project.Application.DTO.User;

import com.taskium.project.Domain.Enums.RoleName;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String name;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido, formato esperado: 000.000.000-00")
    private String cpf;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, max = 20, message = "Senha deve ter entre 6 e 20 caracteres")
    private String password;

    @NotNull(message = "Role é obrigatória")
    private Long roleId;

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone inválido, formato esperado: (00) 00000-0000")
    private String phoneNumber;



    @NotNull(message = "Data de nascimento é obrigatória")
    @Past(message = "Data de nascimento deve ser no passado")
    private LocalDate birthday;

    @Email(message = "Email reserva inválido")
    private String reservedEmail;

    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone reserva inválido, formato esperado: (00) 00000-0000")
    private String reservedPhoneNumber;

    @NotNull(message = "Nome da role é obrigatório")
    private RoleName roleName;

}