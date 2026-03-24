package com.taskium.project.Domain.Common.Exceptions.Event;

public class NoEventsFoundException extends RuntimeException {

    public NoEventsFoundException() {
        super("Nenhum evento encontrado.");
    }
}
