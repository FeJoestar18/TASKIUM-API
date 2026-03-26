package com.taskium.project.Domain.Common.Validators;

import com.taskium.project.Domain.Common.Exceptions.Common.CpflAlreadyExistsException;
import com.taskium.project.Domain.Common.Exceptions.Common.EmailAlreadyExistsException;
import com.taskium.project.Domain.Common.Exceptions.Common.PhoneNumberAlreadyExistsException;
import com.taskium.project.Domain.Interfaces.Repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final IUserRepository userRepository;

    public void validateUserUniqueness(String email, String cpf, String phoneNumber, Long currentUserId) {
        boolean emailExists = currentUserId == null 
                ? userRepository.existsByEmail(email) 
                : userRepository.existsByEmailAndIdNot(email, currentUserId);
        
        if (emailExists) {
            throw new EmailAlreadyExistsException();
        }

        boolean cpfExists = currentUserId == null 
                ? userRepository.existsByCpf(cpf) 
                : userRepository.existsByCpfAndIdNot(cpf, currentUserId);

        if (cpfExists) {
            throw new CpflAlreadyExistsException();
        }

        boolean phoneExists = currentUserId == null 
                ? userRepository.existsByPhoneNumber(phoneNumber) 
                : userRepository.existsByPhoneNumberAndIdNot(phoneNumber, currentUserId);

        if (phoneExists) {
            throw new PhoneNumberAlreadyExistsException();
        }
    }
}