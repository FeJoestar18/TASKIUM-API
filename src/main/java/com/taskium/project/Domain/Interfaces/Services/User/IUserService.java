package com.taskium.project.Domain.Interfaces.Services.User;

import com.taskium.project.Domain.Entity.Role;
import com.taskium.project.Domain.Entity.User;
import com.taskium.project.Domain.Entity.UserDetails;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IUserService {
     User createUser(String name, String email, String cpf, String phoneNumber, String password);
     UserDetails createUserDetails(User user, Role role, LocalDate birthday, String reservedEmail, String reservedPhoneNumber);
     Optional<User> getUserById(Long id);
     void deleteUserById(Long id);
     User updateUserById(Long id, String name, String email, String cpf, String phoneNumber, String password);
     UserDetails updateUserDetailsById(Long id, Role role, LocalDate birthday, String reservedEmail, String reservedPhoneNumber);
     List<User> getAllUsers();
}
