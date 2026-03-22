package com.taskium.project.Domain.Interfaces.Services.User;

import com.taskium.project.Application.DTO.UserRequestDTO;
import com.taskium.project.Domain.Entity.Role;
import com.taskium.project.Domain.Entity.User;
import com.taskium.project.Domain.Entity.UserDetails;

public interface IUserService {
     User createUser(UserRequestDTO userRequestDTO);
     UserDetails createUserDetails(User user, Role role, UserRequestDTO userRequestDTO);
     void validateUserUniqueness(UserRequestDTO userRequestDTO);
}
