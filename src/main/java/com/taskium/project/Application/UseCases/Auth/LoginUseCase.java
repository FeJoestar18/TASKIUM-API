package com.taskium.project.Application.UseCases.Auth;

import com.taskium.project.Application.DTO.AuthDTO;
import com.taskium.project.Application.DTO.LoginResponseDTO;
import com.taskium.project.Domain.Entity.User;
import com.taskium.project.Infrastructure.Security.TokenGenerateService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {
    private final AuthenticationManager authenticationManager;
    private final TokenGenerateService tokenGenerateService;

    public LoginUseCase(
            AuthenticationManager authenticationManager,
            TokenGenerateService tokenGenerateService
    ) {
        this.authenticationManager = authenticationManager;
        this.tokenGenerateService = tokenGenerateService;
    }

    public LoginResponseDTO execute(AuthDTO authDTO) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(
                authDTO.getEmail(),
                authDTO.getPassword()
        );

        var auth = authenticationManager.authenticate(usernamePassword);

        User user = (User) auth.getPrincipal();

        var token = tokenGenerateService.generateToken(user);

        return new LoginResponseDTO(token);
    }
}