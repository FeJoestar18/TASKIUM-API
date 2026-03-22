package com.taskium.project.Domain.Services.User;

import com.taskium.project.Application.DTO.UserRequestDTO;
import com.taskium.project.Domain.Entity.Role;
import com.taskium.project.Domain.Entity.User;
import com.taskium.project.Domain.Entity.UserDetails;
import com.taskium.project.Domain.Exceptions.Auth.CpflAlreadyExistsException;
import com.taskium.project.Domain.Exceptions.Auth.EmailAlreadyExistsException;
import com.taskium.project.Domain.Exceptions.Auth.PhoneNumberAlreadyExistsException;
import com.taskium.project.Domain.Interfaces.Repository.IUserRepository;
import com.taskium.project.Domain.Interfaces.Services.User.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            IUserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(UserRequestDTO dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .phoneNumber(dto.getPhoneNumber())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();
    }

    @Override
    public UserDetails createUserDetails(User user, Role role, UserRequestDTO dto) {
        return UserDetails.builder()
                .user(user)
                .role(role)
                .birthday(dto.getBirthday())
                .reservedEmail(dto.getReservedEmail())
                .reservedPhoneNumber(dto.getReservedPhoneNumber())
                .build();
    }

    @Override
    public void validateUserUniqueness(UserRequestDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        if (userRepository.existsByCpf(dto.getCpf())) {
            throw new CpflAlreadyExistsException();
        }

        if (userRepository.existsByPhoneNumber(dto.getPhoneNumber())) {
            throw new PhoneNumberAlreadyExistsException();
        }
    }
}