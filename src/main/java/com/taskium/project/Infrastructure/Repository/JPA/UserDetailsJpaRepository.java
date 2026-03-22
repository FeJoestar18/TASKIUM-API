package com.taskium.project.Infrastructure.Repository.JPA;

import com.taskium.project.Domain.Entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsJpaRepository extends JpaRepository<UserDetails, Long> {
    Optional<UserDetails> findByUserId(Long userId);
}