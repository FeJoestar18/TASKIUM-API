package com.taskium.project.Api.Controllers.User;

import com.taskium.project.Application.DTO.UserGetResponseDTO;
import com.taskium.project.Application.DTO.UserRequestDTO;
import com.taskium.project.Application.DTO.UserResponseDTO;
import com.taskium.project.Application.UseCases.User.GetUserByIdUseCase;
import com.taskium.project.Application.UseCases.User.RegisterUserUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;

    public UserController(
            RegisterUserUseCase registerUserUseCase,
            GetUserByIdUseCase getUserByIdUseCase
    ) {
        this.registerUserUseCase = registerUserUseCase;
        this.getUserByIdUseCase = getUserByIdUseCase;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRequestDTO dto) {

        var response = registerUserUseCase.execute(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGetResponseDTO> getById(@PathVariable Long id) {

        return ResponseEntity.ok(getUserByIdUseCase.execute(id));
    }
}