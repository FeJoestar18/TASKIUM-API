package com.taskium.project.Domain.Services.Auth;

import com.taskium.project.Application.DTO.UserRequestDTO;
import com.taskium.project.Domain.Entity.Role;
import com.taskium.project.Domain.Entity.User;
import com.taskium.project.Domain.Entity.UserDetails;
import com.taskium.project.Domain.Exceptions.Auth.CpflAlreadyExistsException;
import com.taskium.project.Domain.Exceptions.Auth.EmailAlreadyExistsException;
import com.taskium.project.Domain.Exceptions.Auth.PhoneNumberAlreadyExistsException;
import com.taskium.project.Domain.Interfaces.Services.Auth.IAuthService;
import com.taskium.project.Infrastructure.Repository.RoleRepository;
import com.taskium.project.Infrastructure.Repository.UserDetailsRepository;
import com.taskium.project.Infrastructure.Repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;

@Service
public class AuthService implements IAuthService {

    private final UserDetailsRepository userDetailsRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, UserDetailsRepository userDetailsRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public void registerUser(UserRequestDTO dto) throws RoleNotFoundException {

        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .phoneNumber(dto.getPhoneNumber())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        if (userRepository.existsByCpf(dto.getCpf())) {
            throw new CpflAlreadyExistsException();
        }

        if (userRepository.existsByPhoneNumber(dto.getPhoneNumber())) {
            throw new PhoneNumberAlreadyExistsException();
        }

        userRepository.save(user);

        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new RoleNotFoundException());

        UserDetails userDetails = UserDetails.builder()
                .user(user)
                .role(role)
                .birthday(dto.getBirthday())
                .reservedEmail(dto.getReservedEmail())
                .reservedPhoneNumber(dto.getReservedPhoneNumber())
                .build();

        userDetailsRepository.save(userDetails);


        user.setUserDetail(userDetails);
    }
}