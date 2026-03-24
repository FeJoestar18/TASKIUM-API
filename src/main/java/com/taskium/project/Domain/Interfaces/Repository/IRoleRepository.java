package com.taskium.project.Domain.Interfaces.Repository;

import com.taskium.project.Domain.Entity.Role;
import com.taskium.project.Domain.Enums.RoleName;

import java.util.Optional;

public interface IRoleRepository {
    Optional<Role> findByName(RoleName name);
    Optional<Role> findById(Long id);
}
