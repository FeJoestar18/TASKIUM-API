package com.taskium.project.Domain.Common.Exceptions.Permission;

public class PermissionNotFoundException extends RuntimeException {

    public PermissionNotFoundException(Long id) {
        super("Permissão não encontrada com ID: " + id);
    }

    public PermissionNotFoundException(String message) {
        super(message);
    }
}
