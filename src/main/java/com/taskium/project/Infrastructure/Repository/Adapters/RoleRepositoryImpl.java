package com.taskium.project.Infrastructure.Repository.Adapters;

import com.taskium.project.Domain.Entity.Role;
import com.taskium.project.Domain.Enums.RoleName;
import com.taskium.project.Domain.Interfaces.Repository.IRoleRepository;
import com.taskium.project.Infrastructure.Repository.JPA.RoleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements IRoleRepository {
    private final RoleJpaRepository roleJpaRepository;

    public RoleRepositoryImpl(RoleJpaRepository roleJpaRepository){
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleJpaRepository.findByName(name);
    }
}
