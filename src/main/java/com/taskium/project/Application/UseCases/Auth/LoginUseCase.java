package com.taskium.project.Application.UseCases.Auth;

import com.taskium.project.Application.DTO.Auth.AuthDTO;
import com.taskium.project.Application.DTO.Auth.LoginResponseDTO;
import com.taskium.project.Domain.Entity.RefreshToken;
import com.taskium.project.Infrastructure.Security.AuthenticatedUserDetails;
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

        var authenticatedUser = (AuthenticatedUserDetails) auth.getPrincipal();

        var accessToken = tokenGenerateService.generateToken(authenticatedUser);
        RefreshToken refreshToken = tokenGenerateService.generateRefreshToken(authenticatedUser.getDomainUser());

        return new LoginResponseDTO(
                accessToken,
                refreshToken.getToken(),
                tokenGenerateService.getAccessExpirationSeconds()
        );
    }
}