package com.taskium.project.Domain.Services.User;

import com.taskium.project.Application.DTO.User.UserRequestDTO;
import com.taskium.project.Domain.Common.Exceptions.User.UserNotFoundException;
import com.taskium.project.Domain.Entity.Role;
import com.taskium.project.Domain.Entity.User;
import com.taskium.project.Domain.Entity.UserDetails;
import com.taskium.project.Domain.Interfaces.Repository.IUserDetailsRepository;
import com.taskium.project.Domain.Interfaces.Repository.IUserRepository;
import com.taskium.project.Domain.Interfaces.Services.User.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final IUserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            IUserRepository userRepository,
            PasswordEncoder passwordEncoder,
            IUserDetailsRepository userDetailsRepository
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsRepository = userDetailsRepository;
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
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUserById(Long id) {

        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        userRepository.deleteById(id);
    }

    @Override
    public User updateUserById(Long id, UserRequestDTO userRequestDTO) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setCpf(userRequestDTO.getCpf());
        user.setPhoneNumber(userRequestDTO.getPhoneNumber());

        if (userRequestDTO.getPassword() != null && !userRequestDTO.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        }

        userRepository.save(user);

        return user;
    }

    @Override
    public UserDetails updateUserDetailsById(Long id, Role role, UserRequestDTO userRequestDTO) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        UserDetails userDetails = userDetailsRepository.findByUserId(id)
                .orElse(null);

        if (userDetails == null) {
            userDetails = new UserDetails();
            userDetails.setUser(user);
        }

        userDetails.setRole(role);
        userDetails.setBirthday(userRequestDTO.getBirthday());
        userDetails.setReservedEmail(userRequestDTO.getReservedEmail());
        userDetails.setReservedPhoneNumber(userRequestDTO.getReservedPhoneNumber());

        userDetailsRepository.save(userDetails);
        
        return userDetails;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}