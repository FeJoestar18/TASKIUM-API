package com.taskium.project.Domain.Common.Exceptions.Status;

public class StatusNotFoundException extends RuntimeException {

    public StatusNotFoundException(Long id) {
        super("Status não encontrado com ID: " + id);
    }

    public StatusNotFoundException(String message) {
        super(message);
    }
}

