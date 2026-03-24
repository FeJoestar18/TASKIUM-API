package com.taskium.project.Domain.Common.Exceptions.Event;

public class EventUserNotFoundException extends RuntimeException {

    public EventUserNotFoundException(Long eventId, Long userId) {
        super("Participação não encontrada para usuário " + userId + " no evento " + eventId + ".");
    }
}
