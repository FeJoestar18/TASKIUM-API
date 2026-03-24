package com.taskium.project.Application.UseCases.Note;

import com.taskium.project.Application.DTO.Note.NoteListResponseDTO;
import com.taskium.project.Domain.Interfaces.Services.Note.INoteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllNotesUseCase {

    private final INoteService noteService;

    public GetAllNotesUseCase(INoteService noteService) {
        this.noteService = noteService;
    }

    public List<NoteListResponseDTO> execute(String authenticatedEmail) {
        return noteService.getAllNotes(authenticatedEmail)
                .stream()
                .map(NoteListResponseDTO::from)
                .toList();
    }
}
