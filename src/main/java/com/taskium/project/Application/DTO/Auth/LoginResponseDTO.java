package com.taskium.project.Application.DTO.Auth;

public record LoginResponseDTO(
        String accessToken,
        String refreshToken,
        long expiresIn,
        String tokenType
) {
    public LoginResponseDTO(String accessToken, String refreshToken, long expiresIn) {
        this(accessToken, refreshToken, expiresIn, "Bearer");
    }
}
