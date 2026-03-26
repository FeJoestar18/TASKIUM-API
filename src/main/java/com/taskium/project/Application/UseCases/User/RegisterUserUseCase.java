package com.taskium.project.Application.UseCases.User;

import com.taskium.project.Application.DTO.User.UserRequestDTO;
import com.taskium.project.Application.DTO.User.UserResponseDTO;
import com.taskium.project.Domain.Common.Exceptions.Role.RoleNotFoundException;
import com.taskium.project.Domain.Interfaces.Repository.IRoleRepository;
import com.taskium.project.Domain.Interfaces.Repository.IUserDetailsRepository;
import com.taskium.project.Domain.Interfaces.Repository.IUserRepository;
import com.taskium.project.Domain.Interfaces.Services.User.IUserService;

import com.taskium.project.Domain.Common.Validators.UserValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterUserUseCase {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final IUserDetailsRepository userDetailsRepository;
    private final IUserService userService;
    private final UserValidator userValidator;


    public RegisterUserUseCase(
            IUserRepository userRepository,
            IRoleRepository roleRepository,
            IUserDetailsRepository userDetailsRepository,
            IUserService userService,
            UserValidator userValidator
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @Transactional
    public UserResponseDTO execute(UserRequestDTO dto){

        userValidator.validateUserUniqueness(dto.getEmail(), dto.getCpf(), dto.getPhoneNumber(), null);

        var user = userService.createUser(
                dto.getName(),
                dto.getEmail(),
                dto.getCpf(),
                dto.getPhoneNumber(),
                dto.getPassword()
        );
        user = userRepository.save(user);

        var role = roleRepository.findByName(dto.getRoleName())
                .orElseThrow(RoleNotFoundException::new);

        var userDetails = userService.createUserDetails(
                user, 
                role, 
                dto.getBirthday(), 
                dto.getReservedEmail(), 
                dto.getReservedPhoneNumber()
        );

        userDetailsRepository.save(userDetails);

        user.setUserDetail(userDetails);

        return UserResponseDTO.from(user);
    }
}