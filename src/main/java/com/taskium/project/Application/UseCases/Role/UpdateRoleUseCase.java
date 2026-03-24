package com.taskium.project.Application.UseCases.Role;

import com.taskium.project.Application.DTO.Role.RoleResponseDTO;
import com.taskium.project.Application.DTO.Role.UpdateRoleRequestDTO;
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
public class UpdateRoleUseCase {

    private final IRoleRepository roleRepository;
    private final IPermissionRepository permissionRepository;

    public UpdateRoleUseCase(IRoleRepository roleRepository, IPermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Transactional
    public RoleResponseDTO execute(Long id, UpdateRoleRequestDTO dto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(RoleNotFoundException::new);

        if (dto.getDescription() != null) {
            role.setDescription(dto.getDescription());
        }

        if (dto.getPermissionIds() != null) {
            Set<Permission> permissions = permissionRepository.findAllByIdIn(dto.getPermissionIds());
            if (permissions.size() != dto.getPermissionIds().size()) {
                throw new PermissionNotFoundException("Uma ou mais permissões não foram encontradas.");
            }
            role.setPermissions(permissions);
        }

        role = roleRepository.save(role);
        return RoleResponseDTO.from(role);
    }
}

