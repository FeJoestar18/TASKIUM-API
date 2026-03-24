package com.taskium.project.Domain.Interfaces.Repository;

import com.taskium.project.Domain.Entity.Role;
import com.taskium.project.Domain.Enums.RoleName;

import java.util.List;
import java.util.Optional;

public interface IRoleRepository {
    Optional<Role> findByName(RoleName name);
    Optional<Role> findById(Long id);
    Role save(Role role);
    void deleteById(Long id);
    boolean existsById(Long id);
    boolean existsByName(RoleName name);
    List<Role> findAll();
}
