package com.taskium.project.Infrastructure.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskium.project.Application.DTO.Common.ErrorResponseDTO;
import com.taskium.project.Domain.Common.Exceptions.Auth.InvalidTokenException;
import com.taskium.project.Domain.Services.Auth.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenGenerateService tokenService;
    private final AuthService authService;
    private final ObjectMapper objectMapper;

    public SecurityFilter(TokenGenerateService tokenService, AuthService authService, ObjectMapper objectMapper) {
        this.tokenService = tokenService;
        this.authService = authService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var token = recoverToken(request);

        if (token != null) {
            try {
                var login = tokenService.validateToken(token);
                UserDetails user = authService.loadUserByUsername(login);

                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (InvalidTokenException ex) {
                writeErrorResponse(response, request, HttpStatus.UNAUTHORIZED, "Token inválido", ex.getMessage());
                return;
            } catch (RuntimeException ex) {
                writeErrorResponse(response, request, HttpStatus.UNAUTHORIZED, "Erro de autenticação", "Token inválido ou expirado.");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.substring(7);
    }

    private void writeErrorResponse(HttpServletResponse response, HttpServletRequest request,
                                     HttpStatus status, String error, String message) throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(error)
                .message(message)
                .path(request.getRequestURI())
                .details(Map.of())
                .build();

        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}