package com.taskium.project.Domain.Interfaces.Repository;

import com.taskium.project.Domain.Entity.Permission;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IPermissionRepository {
    Optional<Permission> findByName(String name);
    Optional<Permission> findById(Long id);
    Permission save(Permission permission);
    List<Permission> findAll();
    Set<Permission> findAllByIdIn(Set<Long> ids);
    boolean existsByName(String name);
}

