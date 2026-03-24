package com.taskium.project.Application.UseCases.Permission;

import com.taskium.project.Application.DTO.Permission.PermissionResponseDTO;
import com.taskium.project.Domain.Interfaces.Repository.IPermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllPermissionsUseCase {

    private final IPermissionRepository permissionRepository;

    public GetAllPermissionsUseCase(IPermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public List<PermissionResponseDTO> execute() {
        return permissionRepository.findAll().stream()
                .map(PermissionResponseDTO::from)
                .collect(Collectors.toList());
    }
}

