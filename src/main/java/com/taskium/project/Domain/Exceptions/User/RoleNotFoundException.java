package com.taskium.project.Domain.Exceptions.User;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException() {
        super("Role não encontrada");
    }

    public RoleNotFoundException(String message) {
        super(message);
    }
}
