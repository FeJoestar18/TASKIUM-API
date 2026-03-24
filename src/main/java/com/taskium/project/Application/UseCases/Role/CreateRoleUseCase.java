package com.taskium.project.Application.UseCases.Role;

import com.taskium.project.Application.DTO.Role.CreateRoleRequestDTO;
import com.taskium.project.Application.DTO.Role.RoleResponseDTO;
import com.taskium.project.Domain.Common.Exceptions.Permission.PermissionNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.Role.RoleAlreadyExistsException;
import com.taskium.project.Domain.Entity.Permission;
import com.taskium.project.Domain.Entity.Role;
import com.taskium.project.Domain.Interfaces.Repository.IPermissionRepository;
import com.taskium.project.Domain.Interfaces.Repository.IRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class CreateRoleUseCase {

    private final IRoleRepository roleRepository;
    private final IPermissionRepository permissionRepository;

    public CreateRoleUseCase(IRoleRepository roleRepository, IPermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Transactional
    public RoleResponseDTO execute(CreateRoleRequestDTO dto) {
        if (roleRepository.existsByName(dto.getName())) {
            throw new RoleAlreadyExistsException(dto.getName().name());
        }

        Set<Permission> permissions = new HashSet<>();
        if (dto.getPermissionIds() != null && !dto.getPermissionIds().isEmpty()) {
            permissions = permissionRepository.findAllByIdIn(dto.getPermissionIds());
            if (permissions.size() != dto.getPermissionIds().size()) {
                throw new PermissionNotFoundException("Uma ou mais permissões não foram encontradas.");
            }
        }

        Role role = Role.builder()
                .name(dto.getName())
                .description(dto.getDescription() != null ? dto.getDescription() : dto.getName().name() + " role")
                .permissions(permissions)
                .build();

        role = roleRepository.save(role);
        return RoleResponseDTO.from(role);
    }
}

