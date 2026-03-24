package com.taskium.project.Domain.Common.Exceptions.Note;

public class NoteNotFoundException extends RuntimeException {

    public NoteNotFoundException(Long id) {
        super("Não existe nota com esse ID: " + id);
    }
}
