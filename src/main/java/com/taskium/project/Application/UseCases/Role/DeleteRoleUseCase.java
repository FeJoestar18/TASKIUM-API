package com.taskium.project.Application.UseCases.Role;

import com.taskium.project.Domain.Common.Exceptions.Role.RoleNotFoundException;
import com.taskium.project.Domain.Interfaces.Repository.IRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteRoleUseCase {

    private final IRoleRepository roleRepository;

    public DeleteRoleUseCase(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void execute(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RoleNotFoundException();
        }
        roleRepository.deleteById(id);
    }
}

