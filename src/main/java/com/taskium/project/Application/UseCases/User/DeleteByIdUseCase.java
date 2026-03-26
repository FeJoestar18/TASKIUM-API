package com.taskium.project.Application.UseCases.User;

import com.taskium.project.Domain.Interfaces.Repository.IUserRepository;
import com.taskium.project.Domain.Interfaces.Services.User.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteByIdUseCase {

    private final IUserService userService;
    private final IUserRepository userRepository;

    public DeleteByIdUseCase(IUserService userService, IUserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Transactional
    public void execute(Long id) {
        userService.deleteUserById(id);
        userRepository.deleteById(id);
    }
}
