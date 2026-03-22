package com.taskium.project.Api.Controllers.Auth;

import com.taskium.project.Application.DTO.AuthDTO;
import com.taskium.project.Application.DTO.LoginResponseDTO;
import com.taskium.project.Application.DTO.UserRequestDTO;
import com.taskium.project.Domain.Entity.User;
import com.taskium.project.Domain.Interfaces.Services.Auth.IAuthService;
import com.taskium.project.Infrastructure.Security.TokenGenerateService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.management.relation.RoleNotFoundException;

@RestController
@RequestMapping("auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final IAuthService authService;
    private final TokenGenerateService tokenGenerateService;

    public AuthController(
            AuthenticationManager authenticationManager,
            IAuthService authService,
            TokenGenerateService tokenGenerateService
    ) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.tokenGenerateService = tokenGenerateService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated AuthDTO authDTO) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(
                authDTO.getEmail(),
                authDTO.getPassword()
        );

        var auth = authenticationManager.authenticate(usernamePassword);

        User user = (User) auth.getPrincipal();

        var token = tokenGenerateService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequestDTO dto) throws RoleNotFoundException {
        authService.registerUser(dto);

        return ResponseEntity.ok("Registro realizado com sucesso");
    }
}
