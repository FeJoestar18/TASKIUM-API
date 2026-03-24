package com.taskium.project.Domain.Interfaces.Repository;

import com.taskium.project.Domain.Entity.Permission;

import java.util.List;
import java.util.Optional;

public interface IPermissionRepository {
    Optional<Permission> findByName(String name);
    Permission save(Permission permission);
    List<Permission> findAll();
}

