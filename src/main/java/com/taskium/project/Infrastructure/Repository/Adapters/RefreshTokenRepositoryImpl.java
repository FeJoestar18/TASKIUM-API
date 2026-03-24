package com.taskium.project.Infrastructure.Repository.Adapters;

import com.taskium.project.Domain.Entity.RefreshToken;
import com.taskium.project.Domain.Interfaces.Repository.IRefreshTokenRepository;
import com.taskium.project.Infrastructure.Repository.JPA.RefreshTokenJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class RefreshTokenRepositoryImpl implements IRefreshTokenRepository {

    private final RefreshTokenJpaRepository refreshTokenJpaRepository;

    public RefreshTokenRepositoryImpl(RefreshTokenJpaRepository refreshTokenJpaRepository) {
        this.refreshTokenJpaRepository = refreshTokenJpaRepository;
    }

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        return refreshTokenJpaRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenJpaRepository.findByToken(token);
    }

    @Override
    @Transactional
    public void revokeAllByUserId(Long userId) {
        refreshTokenJpaRepository.revokeAllByUserId(userId);
    }
}

