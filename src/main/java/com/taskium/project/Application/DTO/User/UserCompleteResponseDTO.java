package com.taskium.project.Application.DTO.User;

import lombok.*;

import java.util.List;

@Getter
@Builder
public class UserCompleteResponseDTO {

    private final Long userId;
    private final List<AddressResponseDTO> addresses;
    private final DetailsResponseDTO details;
    private final String message;
}

