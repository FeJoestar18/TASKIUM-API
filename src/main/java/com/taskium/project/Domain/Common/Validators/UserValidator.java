package com.taskium.project.Domain.Common.Validators;

import com.taskium.project.Application.DTO.UserRequestDTO;
import com.taskium.project.Domain.Common.Exceptions.CpflAlreadyExistsException;
import com.taskium.project.Domain.Common.Exceptions.EmailAlreadyExistsException;
import com.taskium.project.Domain.Common.Exceptions.PhoneNumberAlreadyExistsException;
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