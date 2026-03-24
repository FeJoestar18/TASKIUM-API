package com.taskium.project.Application.UseCases.Auth;

import com.taskium.project.Application.DTO.Auth.LoginResponseDTO;
import com.taskium.project.Application.DTO.Auth.RefreshTokenRequestDTO;
import com.taskium.project.Domain.Entity.RefreshToken;
import com.taskium.project.Domain.Entity.User;
import com.taskium.project.Infrastructure.Security.AuthenticatedUserDetails;
import com.taskium.project.Infrastructure.Security.TokenGenerateService;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenUseCase {

    private final TokenGenerateService tokenGenerateService;

    public RefreshTokenUseCase(TokenGenerateService tokenGenerateService) {
        this.tokenGenerateService = tokenGenerateService;
    }

    public LoginResponseDTO execute(RefreshTokenRequestDTO dto) {
        // Validate the existing refresh token
        RefreshToken existingRefreshToken = tokenGenerateService.validateRefreshToken(dto.getRefreshToken());

        User user = existingRefreshToken.getUser();
        AuthenticatedUserDetails authenticatedUser = new AuthenticatedUserDetails(user);

        // Generate new access token
        String newAccessToken = tokenGenerateService.generateToken(authenticatedUser);

        // Generate new refresh token (rotates old one)
        RefreshToken newRefreshToken = tokenGenerateService.generateRefreshToken(user);

        return new LoginResponseDTO(
                newAccessToken,
                newRefreshToken.getToken(),
                tokenGenerateService.getAccessExpirationSeconds()
        );
    }
}

