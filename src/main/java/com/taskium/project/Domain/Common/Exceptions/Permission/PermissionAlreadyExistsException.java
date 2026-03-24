package com.taskium.project.Domain.Common.Exceptions.Permission;

public class PermissionAlreadyExistsException extends RuntimeException {

    public PermissionAlreadyExistsException(String name) {
        super("Permissão com o nome '" + name + "' já existe.");
    }
}
