package com.taskium.project.Domain.Common.Exceptions.Role;

public class RoleAlreadyExistsException extends RuntimeException {

    public RoleAlreadyExistsException() {
        super("Role com este nome já existe.");
    }

    public RoleAlreadyExistsException(String name) {
        super("Role com o nome '" + name + "' já existe.");
    }
}

