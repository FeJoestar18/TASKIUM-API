package com.taskium.project.Domain.Exceptions.Auth;

public class RoleNotFound extends RuntimeException {

    public RoleNotFound() {
        super("Role não encontrada");
    }

    public RoleNotFound(String message) {
        super(message);
    }
}
