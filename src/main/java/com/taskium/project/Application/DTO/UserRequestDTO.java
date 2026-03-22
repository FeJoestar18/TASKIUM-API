package com.taskium.project.Application.DTO;

import com.taskium.project.Domain.Enums.RoleName;
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
    private String phoneNumber;
    private LocalDate birthday;
    private String reservedEmail;
    private String reservedPhoneNumber;
    private RoleName roleName;

}