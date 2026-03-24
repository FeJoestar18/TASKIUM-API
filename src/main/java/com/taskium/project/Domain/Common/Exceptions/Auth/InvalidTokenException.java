package com.taskium.project.Domain.Common.Exceptions.Auth;

public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException() {
        super("Token inválido ou expirado.");
    }

    public InvalidTokenException(String message) {
        super(message);
    }
}

