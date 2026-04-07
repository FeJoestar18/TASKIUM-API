package com.taskium.project.Infrastructure.Security;

import com.taskium.project.Domain.Common.Exceptions.Auth.InvalidTokenException;
import com.taskium.project.Domain.Entity.AccessTokenBlacklist;
import com.taskium.project.Domain.Entity.RefreshToken;
import com.taskium.project.Domain.Entity.User;
import com.taskium.project.Domain.Interfaces.Repository.IAccessTokenBlacklistRepository;
import com.taskium.project.Domain.Interfaces.Repository.IRefreshTokenRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.UUID;

@Service
public class TokenGenerateService {

    private static final Logger logger = LoggerFactory.getLogger(TokenGenerateService.class);

    @Value("${api.security.jwt.secret}")
    private String secretKey;

    @Value("${api.security.jwt.access-expiration:3600}")
    private long accessExpirationSeconds;

    @Value("${api.security.jwt.refresh-expiration:604800}")
    private long refreshExpirationSeconds;

    private final IRefreshTokenRepository refreshTokenRepository;
    private final IAccessTokenBlacklistRepository accessTokenBlacklistRepository;

    public TokenGenerateService(IRefreshTokenRepository refreshTokenRepository,
            IAccessTokenBlacklistRepository accessTokenBlacklistRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.accessTokenBlacklistRepository = accessTokenBlacklistRepository;
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

    public DecodedJWT decodeToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("taskium")
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception) {
            throw new InvalidTokenException("Token inválido: " + exception.getMessage());
        }
    }

    public String validateToken(String token) {
        return decodeToken(token).getSubject();
    }

    @Transactional
    public RefreshToken generateRefreshToken(User user) {

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

    @Transactional
    public void blacklistAccessToken(String token, Instant expiresAt) {
        logger.info("Blacklisting access token: {} expira em {}", token, expiresAt);
        AccessTokenBlacklist blacklist = AccessTokenBlacklist.builder()
                .token(token)
                .expiresAt(expiresAt)
                .build();
        accessTokenBlacklistRepository.save(blacklist);
    }

    public boolean isAccessTokenBlacklisted(String token) {
        boolean blacklisted = accessTokenBlacklistRepository.findByToken(token).isPresent();
        logger.info("Verificando blacklist para token: {} - Blacklisted? {}", token, blacklisted);
        return blacklisted;
    }

    public long getAccessExpirationSeconds() {
        return accessExpirationSeconds;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
