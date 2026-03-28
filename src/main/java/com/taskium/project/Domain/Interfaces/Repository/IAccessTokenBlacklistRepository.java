package com.taskium.project.Domain.Interfaces.Repository;

import com.taskium.project.Domain.Entity.AccessTokenBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface IAccessTokenBlacklistRepository extends JpaRepository<AccessTokenBlacklist, Long> {
    Optional<AccessTokenBlacklist> findByToken(String token);

    @Modifying
    @Query("DELETE FROM AccessTokenBlacklist a WHERE a.expiresAt < :now")
    void deleteAllExpiredSince(@Param("now") Instant now);
}

