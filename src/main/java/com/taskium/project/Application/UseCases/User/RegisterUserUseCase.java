package com.taskium.project.Application.UseCases.User;

import com.taskium.project.Application.DTO.UserRequestDTO;
import com.taskium.project.Domain.Exceptions.Auth.RoleNotFoundException;
import com.taskium.project.Domain.Interfaces.Repository.IRoleRepository;
import com.taskium.project.Domain.Interfaces.Repository.IUserDetailsRepository;
import com.taskium.project.Domain.Interfaces.Repository.IUserRepository;
import com.taskium.project.Domain.Interfaces.Services.User.IUserService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterUserUseCase {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final IUserDetailsRepository userDetailsRepository;
    private final IUserService userService;

    public RegisterUserUseCase(
            IUserRepository userRepository,
            IRoleRepository roleRepository,
            IUserDetailsRepository userDetailsRepository,
            IUserService userService
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.userService = userService;
    }

    @Transactional
    public void execute(UserRequestDTO dto){

        userService.validateUserUniqueness(dto);

        var user = userService.createUser(dto);
        userRepository.save(user);

        var role = roleRepository.findByName(dto.getRoleName())
                .orElseThrow(RoleNotFoundException::new);

        var userDetails = userService.createUserDetails(user, role, dto);

        userDetailsRepository.save(userDetails);

        user.setUserDetail(userDetails);
    }
}