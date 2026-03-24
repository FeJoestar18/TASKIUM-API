package com.taskium.project.Domain.Common.Exceptions.User;

public class NoUsersFoundException extends RuntimeException {
    public NoUsersFoundException() {
        super("Nenhum usuário encontrado.");
    }
    public NoUsersFoundException(String message) {
        super(message);
    }
}

