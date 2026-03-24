package com.taskium.project.Application.UseCases.User;

import com.taskium.project.Application.DTO.UserRequestDTO;
import com.taskium.project.Domain.Interfaces.Services.User.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserUseCase {
    
    private final IUserService userService;
    
    public UpdateUserUseCase(
            IUserService userService
    ){
        this.userService = userService;
    }
    
    public void execute(Long id, UserRequestDTO dto) {
        
        var user = userService.updateUserById(id, dto);
        
        userService.updateUserDetailsById(id, user.getUserDetail().getRole(), dto);
    }
}
