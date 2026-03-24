package com.taskium.project.Infrastructure.Repository.JPA;

import com.taskium.project.Domain.Entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionJpaRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByName(String name);
}

