package com.taskium.project.Application.UseCases.Role;

import com.taskium.project.Application.DTO.Role.RoleResponseDTO;
import com.taskium.project.Domain.Interfaces.Repository.IRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllRolesUseCase {

    private final IRoleRepository roleRepository;

    public GetAllRolesUseCase(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleResponseDTO> execute() {
        return roleRepository.findAll().stream()
                .map(RoleResponseDTO::from)
                .collect(Collectors.toList());
    }
}

