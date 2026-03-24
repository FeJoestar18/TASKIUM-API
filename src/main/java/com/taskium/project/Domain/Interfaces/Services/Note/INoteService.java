package com.taskium.project.Domain.Interfaces.Services.Note;

import com.taskium.project.Application.DTO.Note.CreateNoteRequestDTO;
import com.taskium.project.Application.DTO.Note.UpdateNoteRequestDTO;
import com.taskium.project.Domain.Entity.Note;

import java.util.List;

public interface INoteService {
    Note createNote(CreateNoteRequestDTO dto, String authenticatedEmail);
    Note updateNoteById(Long id, UpdateNoteRequestDTO dto, String authenticatedEmail);
    void deleteNoteById(Long id, String authenticatedEmail);
    List<Note> getAllNotes(String authenticatedEmail);
    Note getNoteById(Long id, String authenticatedEmail);
    List<Note> getNotesByUser(Long userId, String authenticatedEmail);
}
