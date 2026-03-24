package com.taskium.project.Application.UseCases.User;

import com.taskium.project.Application.DTO.UserGetResponseDTO;
import com.taskium.project.Domain.Interfaces.Services.User.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllUsersUseCase {

    private final IUserService userService;

    public GetAllUsersUseCase(
            IUserService userService
    ) {
        this.userService = userService;
    }

    public List<UserGetResponseDTO> execute() {

        return userService.getAllUsers().stream()
                .map(user -> UserGetResponseDTO.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .cpf(user.getCpf())
                        .phoneNumber(user.getPhoneNumber())
                        .build())
                .toList();
    }
}

