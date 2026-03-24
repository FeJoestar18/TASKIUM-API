package com.taskium.project.Application.UseCases.Role;

import com.taskium.project.Application.DTO.Role.RolePermissionsRequestDTO;
import com.taskium.project.Application.DTO.Role.RoleResponseDTO;
import com.taskium.project.Domain.Common.Exceptions.Permission.PermissionNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.Role.RoleNotFoundException;
import com.taskium.project.Domain.Entity.Permission;
import com.taskium.project.Domain.Entity.Role;
import com.taskium.project.Domain.Interfaces.Repository.IPermissionRepository;
import com.taskium.project.Domain.Interfaces.Repository.IRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class AddPermissionsToRoleUseCase {

    private final IRoleRepository roleRepository;
    private final IPermissionRepository permissionRepository;

    public AddPermissionsToRoleUseCase(IRoleRepository roleRepository, IPermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Transactional
    public RoleResponseDTO execute(Long roleId, RolePermissionsRequestDTO dto) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(RoleNotFoundException::new);

        Set<Permission> permissions = permissionRepository.findAllByIdIn(dto.getPermissionIds());
        if (permissions.size() != dto.getPermissionIds().size()) {
            throw new PermissionNotFoundException("Uma ou mais permissões não foram encontradas.");
        }

        role.getPermissions().addAll(permissions);
        role = roleRepository.save(role);
        return RoleResponseDTO.from(role);
    }
}

