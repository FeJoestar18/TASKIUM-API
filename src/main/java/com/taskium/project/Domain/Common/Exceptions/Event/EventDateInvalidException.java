package com.taskium.project.Domain.Common.Exceptions.Event;

public class EventDateInvalidException extends RuntimeException {

    public EventDateInvalidException() {
        super("Data de início deve ser menor que a data de fim.");
    }
}
