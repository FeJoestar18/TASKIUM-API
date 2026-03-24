package com.taskium.project.Domain.Common.Exceptions.Event;

public class NoParticipationStatusesFoundException extends RuntimeException {

    public NoParticipationStatusesFoundException() {
        super("Nenhum status de participação encontrado.");
    }
}
