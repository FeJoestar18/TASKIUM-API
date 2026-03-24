package com.taskium.project.Domain.Common.Exceptions.Event;

public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException(Long id) {
        super("Não existe evento com esse ID: " + id);
    }
}
