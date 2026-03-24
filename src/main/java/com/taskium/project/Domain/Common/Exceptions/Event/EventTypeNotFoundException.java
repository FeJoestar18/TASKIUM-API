package com.taskium.project.Domain.Common.Exceptions.Event;

public class EventTypeNotFoundException extends RuntimeException {

    public EventTypeNotFoundException(Long id) {
        super("Não existe tipo de evento com esse ID: " + id);
    }
}
