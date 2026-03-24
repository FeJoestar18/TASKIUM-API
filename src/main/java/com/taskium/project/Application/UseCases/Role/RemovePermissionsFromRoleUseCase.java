package com.taskium.project.Application.UseCases.Role;

import com.taskium.project.Application.DTO.Role.RolePermissionsRequestDTO;
import com.taskium.project.Application.DTO.Role.RoleResponseDTO;
import com.taskium.project.Domain.Common.Exceptions.Role.RoleNotFoundException;
import com.taskium.project.Domain.Entity.Role;
import com.taskium.project.Domain.Interfaces.Repository.IRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RemovePermissionsFromRoleUseCase {

    private final IRoleRepository roleRepository;

    public RemovePermissionsFromRoleUseCase(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public RoleResponseDTO execute(Long roleId, RolePermissionsRequestDTO dto) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(RoleNotFoundException::new);

        role.getPermissions().removeIf(p -> dto.getPermissionIds().contains(p.getId()));
        role = roleRepository.save(role);
        return RoleResponseDTO.from(role);
    }
}

