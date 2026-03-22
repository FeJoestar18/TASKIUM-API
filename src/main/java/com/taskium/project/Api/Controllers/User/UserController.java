package com.taskium.project.Api.Controllers.User;

import com.taskium.project.Application.DTO.UserRequestDTO;
import com.taskium.project.Application.UseCases.User.RegisterUserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;

    public UserController(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody UserRequestDTO dto) {

        registerUserUseCase.execute(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso");
    }
}