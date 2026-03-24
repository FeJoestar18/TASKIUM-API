package com.taskium.project.Domain.Common.Exceptions.Note;

public class UnauthorizedNoteAccessException extends RuntimeException {

    public UnauthorizedNoteAccessException() {
        super("Você não tem permissão para acessar esta nota.");
    }
}
