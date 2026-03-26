package com.taskium.project.Application.UseCases.User;

import com.taskium.project.Application.DTO.User.UserGetResponseDTO;
import com.taskium.project.Application.DTO.User.UserRequestDTO;
import com.taskium.project.Domain.Common.Validators.UserValidator;
import com.taskium.project.Domain.Interfaces.Repository.IUserDetailsRepository;
import com.taskium.project.Domain.Interfaces.Repository.IUserRepository;
import com.taskium.project.Domain.Interfaces.Services.User.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateUserUseCase {

    private final IUserService userService;
    private final UserValidator userValidator;
    private final IUserRepository userRepository;
    private final IUserDetailsRepository userDetailsRepository;

    public UpdateUserUseCase(
            IUserService userService,
            UserValidator userValidator,
            IUserRepository userRepository,
            IUserDetailsRepository userDetailsRepository
    ) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.userRepository = userRepository;
        this.userDetailsRepository = userDetailsRepository;
    }

    @Transactional
    public UserGetResponseDTO execute(Long id, UserRequestDTO dto) {
        
        userValidator.validateUserUniqueness(dto.getEmail(), dto.getCpf(), dto.getPhoneNumber(), id);

        var user = userService.updateUserById(
                id, 
                dto.getName(), 
                dto.getEmail(), 
                dto.getCpf(), 
                dto.getPhoneNumber(), 
                dto.getPassword()
        );
        userRepository.save(user);

        var userDetails = userService.updateUserDetailsById(
                id, 
                user.getUserDetail() != null ? user.getUserDetail().getRole() : null, 
                dto.getBirthday(), 
                dto.getReservedEmail(), 
                dto.getReservedPhoneNumber()
        );
        userDetailsRepository.save(userDetails);

        return UserGetResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .cpf(user.getCpf())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
