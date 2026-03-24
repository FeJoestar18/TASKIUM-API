package com.taskium.project.Api.Controllers.Auth;

import com.taskium.project.Application.DTO.Auth.AuthDTO;
import com.taskium.project.Application.DTO.Auth.LoginResponseDTO;
import com.taskium.project.Application.DTO.Auth.RefreshTokenRequestDTO;
import com.taskium.project.Application.UseCases.Auth.LoginUseCase;
import com.taskium.project.Application.UseCases.Auth.LogoutUseCase;
import com.taskium.project.Application.UseCases.Auth.RefreshTokenUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final LogoutUseCase logoutUseCase;

    @Autowired
    public AuthController(LoginUseCase loginUseCase,
                          RefreshTokenUseCase refreshTokenUseCase,
                          LogoutUseCase logoutUseCase) {
        this.loginUseCase = loginUseCase;
        this.refreshTokenUseCase = refreshTokenUseCase;
        this.logoutUseCase = logoutUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody AuthDTO authDTO) {
        var response = loginUseCase.execute(authDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(@Valid @RequestBody RefreshTokenRequestDTO dto) {
        var response = refreshTokenUseCase.execute(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@Valid @RequestBody RefreshTokenRequestDTO dto) {
        logoutUseCase.execute(dto);
        return ResponseEntity.noContent().build();
    }
}