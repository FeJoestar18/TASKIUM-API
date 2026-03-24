package com.taskium.project.Application.UseCases.Note;

import com.taskium.project.Application.DTO.Note.NoteResponseDTO;
import com.taskium.project.Application.DTO.Note.UpdateNoteRequestDTO;
import com.taskium.project.Domain.Interfaces.Services.Note.INoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateNoteByIdUseCase {

    private final INoteService noteService;

    public UpdateNoteByIdUseCase(INoteService noteService) {
        this.noteService = noteService;
    }

    @Transactional
    public NoteResponseDTO execute(Long id, UpdateNoteRequestDTO dto, String authenticatedEmail) {
        return NoteResponseDTO.from(noteService.updateNoteById(id, dto, authenticatedEmail));
    }
}
