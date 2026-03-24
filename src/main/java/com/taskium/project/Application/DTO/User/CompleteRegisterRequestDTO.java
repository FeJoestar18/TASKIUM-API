package com.taskium.project.Application.DTO.User;

import jakarta.validation.Valid;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompleteRegisterRequestDTO {

    @Valid
    private AddressRequestDTO address;

    @Valid
    private DetailsRequestDTO details;
}

