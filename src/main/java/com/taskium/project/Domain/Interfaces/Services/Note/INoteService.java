package com.taskium.project.Domain.Interfaces.Services.Note;

import com.taskium.project.Domain.Entity.Note;

import java.util.List;

public interface INoteService {
    Note createNote(String name, String description, String noteContent);
    Note updateNoteById(Long id, String name, String description, String noteContent, String authenticatedEmail);
    void validateNoteDeletion(Long id, String authenticatedEmail);
    List<Note> getAllNotes(String authenticatedEmail);
    Note getNoteById(Long id, String authenticatedEmail);
    List<Note> getNotesByUser(Long userId, String authenticatedEmail);
}
