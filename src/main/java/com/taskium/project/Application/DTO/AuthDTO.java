package com.taskium.project.Application.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AuthDTO {

    private String email;
    private String password;

}
