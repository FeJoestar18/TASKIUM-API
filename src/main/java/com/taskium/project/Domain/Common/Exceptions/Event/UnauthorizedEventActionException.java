package com.taskium.project.Domain.Common.Exceptions.Event;

public class UnauthorizedEventActionException extends RuntimeException {

    public UnauthorizedEventActionException() {
        super("Você não tem permissão para alterar este evento.");
    }
}
