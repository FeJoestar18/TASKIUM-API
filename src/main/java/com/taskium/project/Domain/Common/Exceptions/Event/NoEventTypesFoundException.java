package com.taskium.project.Domain.Common.Exceptions.Event;

public class NoEventTypesFoundException extends RuntimeException {

    public NoEventTypesFoundException() {
        super("Nenhum tipo de evento encontrado.");
    }
}
