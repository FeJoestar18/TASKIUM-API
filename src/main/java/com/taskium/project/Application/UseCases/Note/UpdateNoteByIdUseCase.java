package com.taskium.project.Application.UseCases.Note;

import com.taskium.project.Application.DTO.Note.NoteResponseDTO;
import com.taskium.project.Application.DTO.Note.UpdateNoteRequestDTO;
import com.taskium.project.Domain.Interfaces.Repository.INoteRepository;
import com.taskium.project.Domain.Interfaces.Services.Note.INoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateNoteByIdUseCase {

    private final INoteService noteService;
    private final INoteRepository noteRepository;

    public UpdateNoteByIdUseCase(INoteService noteService, INoteRepository noteRepository) {
        this.noteService = noteService;
        this.noteRepository = noteRepository;
    }

    @Transactional
    public NoteResponseDTO execute(Long id, UpdateNoteRequestDTO dto, String authenticatedEmail) {
        var note = noteService.updateNoteById(id, dto.getName(), dto.getDescription(), dto.getNote(), authenticatedEmail);
        noteRepository.save(note);
        return NoteResponseDTO.from(note);
    }
}
