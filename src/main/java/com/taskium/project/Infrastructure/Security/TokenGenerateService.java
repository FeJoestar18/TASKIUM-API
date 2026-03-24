package com.taskium.project.Infrastructure.Security;

import com.taskium.project.Domain.Common.Exceptions.Auth.InvalidTokenException;
import com.taskium.project.Domain.Entity.RefreshToken;
import com.taskium.project.Domain.Entity.User;
import com.taskium.project.Domain.Interfaces.Repository.IRefreshTokenRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class TokenGenerateService {

    @Value("${api.security.jwt.secret}")
    private String secretKey;

    @Value("${api.security.jwt.access-expiration:36000}")
    private long accessExpirationSeconds;

    @Value("${api.security.jwt.refresh-expiration:604800}")
    private long refreshExpirationSeconds;

    private final IRefreshTokenRepository refreshTokenRepository;

    public TokenGenerateService(IRefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public String generateToken(AuthenticatedUserDetails authenticatedUser) {
        try {
            User user = authenticatedUser.getDomainUser();
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.create()
                    .withIssuer("taskium")
                    .withSubject(user.getEmail())
                    .withClaim("userId", user.getId())
                    .withClaim("roles", authenticatedUser.getRoleNames())
                    .withClaim("permissions", authenticatedUser.getPermissionNames())
                    .withExpiresAt(Instant.now().plusSeconds(accessExpirationSeconds))
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token: " + exception.getMessage());
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("taskium")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new InvalidTokenException("Token inválido: " + exception.getMessage());
        }
    }

    @Transactional
    public RefreshToken generateRefreshToken(User user) {
        // Revoke all existing refresh tokens for this user
        refreshTokenRepository.revokeAllByUserId(user.getId());

        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .user(user)
                .expiresAt(Instant.now().plusSeconds(refreshExpirationSeconds))
                .revoked(false)
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken validateRefreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("Refresh token não encontrado."));

        if (refreshToken.isRevoked()) {
            throw new InvalidTokenException("Refresh token foi revogado.");
        }

        if (refreshToken.isExpired()) {
            throw new InvalidTokenException("Refresh token expirado.");
        }

        return refreshToken;
    }

    @Transactional
    public void revokeRefreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("Refresh token não encontrado."));
        refreshToken.setRevoked(true);
        refreshTokenRepository.save(refreshToken);
    }

    public long getAccessExpirationSeconds() {
        return accessExpirationSeconds;
    }
}
