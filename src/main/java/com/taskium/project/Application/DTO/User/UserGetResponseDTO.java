package com.taskium.project.Application.DTO.User;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserGetResponseDTO{

    private Long id;
    private String name;
    private String email;
    private String cpf;
    private String phoneNumber;

}