package com.taskium.project.Domain.Interfaces.Services.User;

import com.taskium.project.Application.DTO.User.UserRequestDTO;
import com.taskium.project.Domain.Entity.Role;
import com.taskium.project.Domain.Entity.User;
import com.taskium.project.Domain.Entity.UserDetails;

import java.util.List;
import java.util.Optional;

public interface IUserService {
     User createUser(UserRequestDTO userRequestDTO);
     UserDetails createUserDetails(User user, Role role, UserRequestDTO userRequestDTO);
     public Optional<User> getUserById(Long id);
     public void deleteUserById(Long id);
     User updateUserById(Long id, UserRequestDTO userRequestDTO);
     UserDetails updateUserDetailsById(Long id, Role role, UserRequestDTO userRequestDTO);
     List<User> getAllUsers();
}
