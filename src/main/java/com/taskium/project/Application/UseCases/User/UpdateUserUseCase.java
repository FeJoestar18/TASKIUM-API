package com.taskium.project.Application.UseCases.User;

import com.taskium.project.Application.DTO.User.UserGetResponseDTO;
import com.taskium.project.Application.DTO.User.UserRequestDTO;
import com.taskium.project.Domain.Interfaces.Services.User.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateUserUseCase {

    private final IUserService userService;

    public UpdateUserUseCase(
            IUserService userService
    ) {
        this.userService = userService;
    }

    @Transactional
    public UserGetResponseDTO execute(Long id, UserRequestDTO dto) {

        var user = userService.updateUserById(id, dto);

        userService.updateUserDetailsById(id, user.getUserDetail().getRole(), dto);

        return UserGetResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .cpf(user.getCpf())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
