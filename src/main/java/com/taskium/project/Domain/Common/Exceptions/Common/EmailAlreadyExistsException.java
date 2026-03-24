package com.taskium.project.Domain.Common.Exceptions.Common;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException() {
        super("Email já está em uso");
    }

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
