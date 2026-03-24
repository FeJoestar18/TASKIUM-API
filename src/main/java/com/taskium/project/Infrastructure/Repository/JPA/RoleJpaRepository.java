package com.taskium.project.Infrastructure.Repository.JPA;

import com.taskium.project.Domain.Entity.Role;
import com.taskium.project.Domain.Enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleJpaRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
    boolean existsByName(RoleName name);
}