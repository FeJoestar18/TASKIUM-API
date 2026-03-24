package com.taskium.project.Domain.Common.Exceptions.Event;

public class UserAlreadyInEventException extends RuntimeException {

    public UserAlreadyInEventException(Long userId, Long eventId) {
        super("Usuário " + userId + " já está vinculado ao evento " + eventId + ".");
    }
}
