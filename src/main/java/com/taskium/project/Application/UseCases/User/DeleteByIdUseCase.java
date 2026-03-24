package com.taskium.project.Application.UseCases.User;

import com.taskium.project.Domain.Interfaces.Services.User.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteByIdUseCase {

    private final IUserService userService;

    public DeleteByIdUseCase(IUserService userService) {
        this.userService = userService;
    }

    @Transactional
    public void execute(Long id) {
        userService.deleteUserById(id);
    }
}
