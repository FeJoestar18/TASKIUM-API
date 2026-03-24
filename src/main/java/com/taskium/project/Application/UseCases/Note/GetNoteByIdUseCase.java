package com.taskium.project.Application.UseCases.Note;

import com.taskium.project.Application.DTO.Note.NoteResponseDTO;
import com.taskium.project.Domain.Interfaces.Services.Note.INoteService;
import org.springframework.stereotype.Service;

@Service
public class GetNoteByIdUseCase {

    private final INoteService noteService;

    public GetNoteByIdUseCase(INoteService noteService) {
        this.noteService = noteService;
    }

    public NoteResponseDTO execute(Long id, String authenticatedEmail) {
        return NoteResponseDTO.from(noteService.getNoteById(id, authenticatedEmail));
    }
}
