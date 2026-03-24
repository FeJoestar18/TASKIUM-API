package com.taskium.project.Infrastructure.Repository.Adapters;

import com.taskium.project.Domain.Entity.Permission;
import com.taskium.project.Domain.Interfaces.Repository.IPermissionRepository;
import com.taskium.project.Infrastructure.Repository.JPA.PermissionJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class PermissionRepositoryImpl implements IPermissionRepository {

    private final PermissionJpaRepository permissionJpaRepository;

    public PermissionRepositoryImpl(PermissionJpaRepository permissionJpaRepository) {
        this.permissionJpaRepository = permissionJpaRepository;
    }

    @Override
    public Optional<Permission> findByName(String name) {
        return permissionJpaRepository.findByName(name);
    }

    @Override
    public Optional<Permission> findById(Long id) {
        return permissionJpaRepository.findById(id);
    }

    @Override
    public Permission save(Permission permission) {
        return permissionJpaRepository.save(permission);
    }

    @Override
    public List<Permission> findAll() {
        return permissionJpaRepository.findAll();
    }

    @Override
    public Set<Permission> findAllByIdIn(Set<Long> ids) {
        return permissionJpaRepository.findAllByIdIn(ids);
    }

    @Override
    public boolean existsByName(String name) {
        return permissionJpaRepository.existsByName(name);
    }
}

