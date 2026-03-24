package com.taskium.project.Application.UseCases.Note;

import com.taskium.project.Domain.Interfaces.Services.Note.INoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteNoteByIdUseCase {

    private final INoteService noteService;

    public DeleteNoteByIdUseCase(INoteService noteService) {
        this.noteService = noteService;
    }

    @Transactional
    public void execute(Long id, String authenticatedEmail) {
        noteService.deleteNoteById(id, authenticatedEmail);
    }
}
