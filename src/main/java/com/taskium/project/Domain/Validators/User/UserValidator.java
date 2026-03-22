package com.taskium.project.Domain.Validators.User;

import com.taskium.project.Application.DTO.UserRequestDTO;
import com.taskium.project.Domain.Exceptions.User.CpflAlreadyExistsException;
import com.taskium.project.Domain.Exceptions.User.EmailAlreadyExistsException;
import com.taskium.project.Domain.Exceptions.User.PhoneNumberAlreadyExistsException;
import com.taskium.project.Domain.Interfaces.Repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final IUserRepository userRepository;

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