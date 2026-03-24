package com.taskium.project.Application.UseCases.Permission;

import com.taskium.project.Application.DTO.Permission.CreatePermissionRequestDTO;
import com.taskium.project.Application.DTO.Permission.PermissionResponseDTO;
import com.taskium.project.Domain.Common.Exceptions.Permission.PermissionAlreadyExistsException;
import com.taskium.project.Domain.Entity.Permission;
import com.taskium.project.Domain.Interfaces.Repository.IPermissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreatePermissionUseCase {

    private final IPermissionRepository permissionRepository;

    public CreatePermissionUseCase(IPermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Transactional
    public PermissionResponseDTO execute(CreatePermissionRequestDTO dto) {
        if (permissionRepository.existsByName(dto.getName())) {
            throw new PermissionAlreadyExistsException(dto.getName());
        }

        Permission permission = Permission.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();

        permission = permissionRepository.save(permission);
        return PermissionResponseDTO.from(permission);
    }
}

