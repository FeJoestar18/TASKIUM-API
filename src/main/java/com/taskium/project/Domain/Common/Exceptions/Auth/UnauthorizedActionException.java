package com.taskium.project.Domain.Common.Exceptions.Auth;

public class UnauthorizedActionException extends RuntimeException {

    public UnauthorizedActionException() {
        super("Você não tem permissão para realizar esta ação.");
    }
}
