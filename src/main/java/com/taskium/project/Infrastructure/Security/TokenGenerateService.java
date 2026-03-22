package com.taskium.project.Infrastructure.Security;

import com.taskium.project.Domain.Entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenGenerateService {

    @Value("${api.security.jwt.secret}")
    public String secretKey;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            String token = JWT.create()
                    .withIssuer("AM")
                    .withSubject(user.getEmail())
                    .withExpiresAt(generateExpirationTime())
                    .sign(algorithm);

            return token;

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token: " + exception.getMessage());
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("AM")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token inválido: " + exception.getMessage());
        }

    }
    private Instant generateExpirationTime() {
        return Instant.now().plusSeconds(3600); // Token válido por 1 hora
    }
}
