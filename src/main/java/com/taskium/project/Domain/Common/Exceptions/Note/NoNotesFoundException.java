package com.taskium.project.Domain.Common.Exceptions.Note;

public class NoNotesFoundException extends RuntimeException {

    public NoNotesFoundException() {
        super("Nenhuma nota encontrada.");
    }
}
