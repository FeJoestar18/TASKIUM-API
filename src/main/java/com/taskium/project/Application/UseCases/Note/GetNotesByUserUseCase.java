package com.taskium.project.Application.UseCases.Note;

import com.taskium.project.Application.DTO.Note.NoteListResponseDTO;
import com.taskium.project.Domain.Interfaces.Services.Note.INoteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetNotesByUserUseCase {

    private final INoteService noteService;

    public GetNotesByUserUseCase(INoteService noteService) {
        this.noteService = noteService;
    }

    public List<NoteListResponseDTO> execute(Long userId, String authenticatedEmail) {
        return noteService.getNotesByUser(userId, authenticatedEmail)
                .stream()
                .map(NoteListResponseDTO::from)
                .toList();
    }
}
