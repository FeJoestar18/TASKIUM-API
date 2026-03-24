package com.taskium.project.Application.UseCases.Role;

import com.taskium.project.Application.DTO.Role.RoleResponseDTO;
import com.taskium.project.Domain.Common.Exceptions.Role.RoleNotFoundException;
import com.taskium.project.Domain.Interfaces.Repository.IRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class GetRoleByIdUseCase {

    private final IRoleRepository roleRepository;

    public GetRoleByIdUseCase(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleResponseDTO execute(Long id) {
        var role = roleRepository.findById(id)
                .orElseThrow(RoleNotFoundException::new);
        return RoleResponseDTO.from(role);
    }
}

