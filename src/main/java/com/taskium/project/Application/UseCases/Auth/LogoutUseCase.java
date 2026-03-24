package com.taskium.project.Application.UseCases.Auth;

import com.taskium.project.Application.DTO.Auth.RefreshTokenRequestDTO;
import com.taskium.project.Infrastructure.Security.TokenGenerateService;
import org.springframework.stereotype.Service;

@Service
public class LogoutUseCase {

    private final TokenGenerateService tokenGenerateService;

    public LogoutUseCase(TokenGenerateService tokenGenerateService) {
        this.tokenGenerateService = tokenGenerateService;
    }

    public void execute(RefreshTokenRequestDTO dto) {
        tokenGenerateService.revokeRefreshToken(dto.getRefreshToken());
    }
}

