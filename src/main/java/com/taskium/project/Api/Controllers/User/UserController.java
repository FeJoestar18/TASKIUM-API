package com.taskium.project.Api.Controllers.User;

import com.taskium.project.Application.DTO.User.UserGetResponseDTO;
import com.taskium.project.Application.DTO.User.UserRequestDTO;
import com.taskium.project.Application.DTO.User.UserResponseDTO;
import com.taskium.project.Application.UseCases.User.DeleteByIdUseCase;
import com.taskium.project.Application.UseCases.User.GetAllUsersUseCase;
import com.taskium.project.Application.UseCases.User.GetUserByIdUseCase;
import com.taskium.project.Application.UseCases.User.RegisterUserUseCase;
import com.taskium.project.Application.UseCases.User.UpdateUserUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final DeleteByIdUseCase deleteByIdUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    public UserController(
            RegisterUserUseCase registerUserUseCase,
            GetUserByIdUseCase getUserByIdUseCase,
            GetAllUsersUseCase getAllUsersUseCase,
            DeleteByIdUseCase deleteByIdUseCase,
            UpdateUserUseCase updateUserUseCase
    ) {
        this.registerUserUseCase = registerUserUseCase;
        this.getUserByIdUseCase = getUserByIdUseCase;
        this.getAllUsersUseCase = getAllUsersUseCase;
        this.deleteByIdUseCase = deleteByIdUseCase;
        this.updateUserUseCase = updateUserUseCase;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRequestDTO dto) {

        var response = registerUserUseCase.execute(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VIEW_USER')")
    public ResponseEntity<UserGetResponseDTO> getById(@PathVariable Long id) {

        return ResponseEntity.ok(getUserByIdUseCase.execute(id));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('MANAGE_USERS')")
    public ResponseEntity<List<UserGetResponseDTO>> getAll() {

        return ResponseEntity.ok(getAllUsersUseCase.execute());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_USER')")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        deleteByIdUseCase.execute(id);

        return ResponseEntity.ok("Usuário deletado com sucesso");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_USER')")
    public ResponseEntity<UserGetResponseDTO> updateById(@PathVariable Long id, @Valid @RequestBody UserRequestDTO dto) {

        var response = updateUserUseCase.execute(id, dto);

        return ResponseEntity.ok(response);
    }
}