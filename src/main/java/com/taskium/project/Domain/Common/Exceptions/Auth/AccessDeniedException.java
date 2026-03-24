package com.taskium.project.Domain.Common.Exceptions.Auth;

public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException() {
        super("Você não tem permissão para acessar este recurso.");
    }

    public AccessDeniedException(String message) {
        super(message);
    }
}

