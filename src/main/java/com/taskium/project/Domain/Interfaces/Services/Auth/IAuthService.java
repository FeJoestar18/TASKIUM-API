package com.taskium.project.Domain.Interfaces.Services.Auth;

import com.taskium.project.Application.DTO.UserRequestDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.management.relation.RoleNotFoundException;

public interface IAuthService extends UserDetailsService {
    void registerUser(UserRequestDTO dto) throws RoleNotFoundException;
}