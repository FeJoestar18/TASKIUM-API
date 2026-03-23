package com.taskium.project.Api.Controllers.Auth;

import com.taskium.project.Application.DTO.AuthDTO;
import com.taskium.project.Application.DTO.LoginResponseDTO;
import com.taskium.project.Application.UseCases.Auth.LoginUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final LoginUseCase loginUseCase;

    @Autowired
    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody AuthDTO authDTO) {

        var response = loginUseCase.execute(authDTO);

        return ResponseEntity.ok(response);
    }
}