package com.taskium.project.Domain.Common.Exceptions;

import org.apache.logging.log4j.message.Message;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) { super("Não existe usuário com esse ID: " + id); }

    public UserNotFoundException(String message) {
        super(message);
    }
}
