package com.taskium.project.Domain.Services.User;

import com.taskium.project.Domain.Common.Exceptions.User.UserNotFoundException;
import com.taskium.project.Domain.Entity.Role;
import com.taskium.project.Domain.Entity.User;
import com.taskium.project.Domain.Entity.UserDetails;
import com.taskium.project.Domain.Interfaces.Repository.IUserDetailsRepository;
import com.taskium.project.Domain.Interfaces.Repository.IUserRepository;
import com.taskium.project.Domain.Interfaces.Services.User.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public User createUser(String name, String email, String cpf, String phoneNumber, String password) {
        return User.builder()
                .name(name)
                .email(email)
                .cpf(cpf)
                .phoneNumber(phoneNumber)
                .password(passwordEncoder.encode(password))
                .build();
    }

    @Override
    public UserDetails createUserDetails(User user, Role role, LocalDate birthday, String reservedEmail, String reservedPhoneNumber) {
        return UserDetails.builder()
                .user(user)
                .role(role)
                .birthday(birthday)
                .reservedEmail(reservedEmail)
                .reservedPhoneNumber(reservedPhoneNumber)
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
    }

    @Override
    public User updateUserById(Long id, String name, String email, String cpf, String phoneNumber, String password) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setName(name);
        user.setEmail(email);
        user.setCpf(cpf);
        user.setPhoneNumber(phoneNumber);

        if (password != null && !password.isBlank()) {
            user.setPassword(passwordEncoder.encode(password));
        }

        return user;
    }

    @Override
    public UserDetails updateUserDetailsById(Long id, Role role, LocalDate birthday, String reservedEmail, String reservedPhoneNumber) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        UserDetails userDetails = userDetailsRepository.findByUserId(id)
                .orElse(null);

        if (userDetails == null) {
            userDetails = new UserDetails();
            userDetails.setUser(user);
            user.setUserDetail(userDetails);
        }

        userDetails.setRole(role);
        userDetails.setBirthday(birthday);
        userDetails.setReservedEmail(reservedEmail);
        userDetails.setReservedPhoneNumber(reservedPhoneNumber);

        return userDetails;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}