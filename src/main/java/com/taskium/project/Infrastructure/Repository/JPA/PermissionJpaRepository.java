package com.taskium.project.Infrastructure.Repository.JPA;

import com.taskium.project.Domain.Entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface PermissionJpaRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByName(String name);
    Set<Permission> findAllByIdIn(Set<Long> ids);
    boolean existsByName(String name);
}

