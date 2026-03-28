package com.taskium.project.Api.Controllers.Auth;

import com.taskium.project.Application.DTO.Auth.AuthDTO;
import com.taskium.project.Application.DTO.Auth.LoginResponseDTO;
import com.taskium.project.Application.DTO.Auth.RefreshTokenRequestDTO;
import com.taskium.project.Application.UseCases.Auth.LoginUseCase;
import com.taskium.project.Application.UseCases.Auth.LogoutUseCase;
import com.taskium.project.Application.UseCases.Auth.RefreshTokenUseCase;
import com.taskium.project.Infrastructure.Security.TokenGenerateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final LogoutUseCase logoutUseCase;
    private final TokenGenerateService tokenGenerateService;

    @Autowired
    public AuthController(LoginUseCase loginUseCase,
                          RefreshTokenUseCase refreshTokenUseCase,
                          LogoutUseCase logoutUseCase,
                          TokenGenerateService tokenGenerateService) {
        this.loginUseCase = loginUseCase;
        this.refreshTokenUseCase = refreshTokenUseCase;
        this.logoutUseCase = logoutUseCase;
        this.tokenGenerateService = tokenGenerateService;
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
    public ResponseEntity<Void> logout(@Valid @RequestBody RefreshTokenRequestDTO dto, HttpServletRequest request) {

        logoutUseCase.execute(dto);

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String accessToken = authHeader.substring(7);

            try {
                var algorithm = com.auth0.jwt.algorithms.Algorithm.HMAC256(tokenGenerateService.getSecretKey());
                var decodedJWT = com.auth0.jwt.JWT.require(algorithm)
                        .withIssuer("taskium")
                        .build()
                        .verify(accessToken);
                java.time.Instant expiresAt = decodedJWT.getExpiresAt().toInstant();
                tokenGenerateService.blacklistAccessToken(accessToken, expiresAt);
            } catch (Exception ignored) {}
        }
        return ResponseEntity.noContent().build();
    }
}