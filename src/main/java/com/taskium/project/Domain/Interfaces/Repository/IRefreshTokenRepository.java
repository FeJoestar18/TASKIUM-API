package com.taskium.project.Domain.Interfaces.Repository;

import com.taskium.project.Domain.Entity.RefreshToken;

import java.util.Optional;

public interface IRefreshTokenRepository {
    RefreshToken save(RefreshToken refreshToken);
    Optional<RefreshToken> findByToken(String token);
    void revokeAllByUserId(Long userId);
}

