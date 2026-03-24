package com.taskium.project.Application.UseCases.Note;

import com.taskium.project.Application.DTO.Note.CreateNoteRequestDTO;
import com.taskium.project.Application.DTO.Note.NoteResponseDTO;
import com.taskium.project.Domain.Interfaces.Services.Note.INoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateNoteUseCase {

    private final INoteService noteService;

    public CreateNoteUseCase(INoteService noteService) {
        this.noteService = noteService;
    }

    @Transactional
    public NoteResponseDTO execute(CreateNoteRequestDTO dto, String authenticatedEmail) {
        return NoteResponseDTO.from(noteService.createNote(dto, authenticatedEmail));
    }
}
