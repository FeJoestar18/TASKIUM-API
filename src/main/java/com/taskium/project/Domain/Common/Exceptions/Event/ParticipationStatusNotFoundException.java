package com.taskium.project.Domain.Common.Exceptions.Event;

public class ParticipationStatusNotFoundException extends RuntimeException {

    public ParticipationStatusNotFoundException(Long id) {
        super("Não existe status de participação com esse ID: " + id);
    }
}
