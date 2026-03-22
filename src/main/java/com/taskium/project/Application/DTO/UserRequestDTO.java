package com.taskium.project.Application.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserRequestDTO {

    private String name;
    private String email;
    private String cpf;
    private String password;
    private Long roleId;
    private String PhoneNumber;
    private LocalDate Birthday;
    private String ReservedEmail;
    private String ReservedPhoneNumber;
}