package com.taskium.project.Application.UseCases.User;

import com.taskium.project.Application.DTO.User.UserGetResponseDTO;
import com.taskium.project.Domain.Common.Exceptions.User.UserNotFoundException;
import com.taskium.project.Domain.Interfaces.Services.User.IUserService;
import org.springframework.stereotype.Service;

@Service
public class GetUserByIdUseCase {

    private final IUserService userService;

    public GetUserByIdUseCase(
            IUserService userService
    ){
        this.userService = userService;
    }

    public UserGetResponseDTO execute(Long id) {

        var user = userService.getUserById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return UserGetResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .cpf(user.getCpf())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
