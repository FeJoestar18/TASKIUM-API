package com.taskium.project.Domain.Services.Note;

import com.taskium.project.Application.DTO.Note.CreateNoteRequestDTO;
import com.taskium.project.Application.DTO.Note.UpdateNoteRequestDTO;
import com.taskium.project.Domain.Common.Exceptions.Note.NoNotesFoundException;
import com.taskium.project.Domain.Common.Exceptions.Note.NoteNotFoundException;
import com.taskium.project.Domain.Common.Exceptions.User.UserNotFoundException;
import com.taskium.project.Domain.Common.Validators.NoteValidator;
import com.taskium.project.Domain.Entity.Note;
import com.taskium.project.Domain.Entity.User;
import com.taskium.project.Domain.Entity.UserNote;
import com.taskium.project.Domain.Interfaces.Repository.INoteRepository;
import com.taskium.project.Domain.Interfaces.Repository.IUserNoteRepository;
import com.taskium.project.Domain.Interfaces.Repository.IUserRepository;
import com.taskium.project.Domain.Interfaces.Services.Note.INoteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService implements INoteService {

    private final INoteRepository noteRepository;
    private final IUserNoteRepository userNoteRepository;
    private final IUserRepository userRepository;
    private final NoteValidator noteValidator;

    public NoteService(
            INoteRepository noteRepository,
            IUserNoteRepository userNoteRepository,
            IUserRepository userRepository,
            NoteValidator noteValidator
    ) {
        this.noteRepository = noteRepository;
        this.userNoteRepository = userNoteRepository;
        this.userRepository = userRepository;
        this.noteValidator = noteValidator;
    }

    @Override
    public Note createNote(CreateNoteRequestDTO dto, String authenticatedEmail) {
        User user = findUserByEmail(authenticatedEmail);

        Note note = noteRepository.save(
                Note.builder()
                        .name(dto.getName())
                        .description(dto.getDescription())
                        .note(dto.getNote())
                        .build()
        );

        userNoteRepository.save(
                UserNote.builder()
                        .user(user)
                        .note(note)
                        .build()
        );

        return note;
    }

    @Override
    public Note updateNoteById(Long id, UpdateNoteRequestDTO dto, String authenticatedEmail) {
        User user = findUserByEmail(authenticatedEmail);
        UserNote userNote = findUserNoteByNoteId(id);
        noteValidator.validateOwnership(userNote.getUser().getId(), user.getId());

        Note note = userNote.getNote();
        note.setName(dto.getName());
        note.setDescription(dto.getDescription());
        note.setNote(dto.getNote());

        return noteRepository.save(note);
    }

    @Override
    public void deleteNoteById(Long id, String authenticatedEmail) {
        User user = findUserByEmail(authenticatedEmail);
        UserNote userNote = findUserNoteByNoteId(id);
        noteValidator.validateOwnership(userNote.getUser().getId(), user.getId());

        userNoteRepository.deleteByNoteId(id);
        noteRepository.deleteById(id);
    }

    @Override
    public List<Note> getAllNotes(String authenticatedEmail) {
        User user = findUserByEmail(authenticatedEmail);
        List<Note> notes = userNoteRepository.findByUserId(user.getId())
                .stream()
                .map(UserNote::getNote)
                .toList();

        if (notes.isEmpty()) {
            throw new NoNotesFoundException();
        }

        return notes;
    }

    @Override
    public Note getNoteById(Long id, String authenticatedEmail) {
        User user = findUserByEmail(authenticatedEmail);
        UserNote userNote = findUserNoteByNoteId(id);
        noteValidator.validateOwnership(userNote.getUser().getId(), user.getId());
        return userNote.getNote();
    }

    @Override
    public List<Note> getNotesByUser(Long userId, String authenticatedEmail) {
        User authenticatedUser = findUserByEmail(authenticatedEmail);
        noteValidator.validateOwnership(userId, authenticatedUser.getId());

        List<Note> notes = userNoteRepository.findByUserId(userId)
                .stream()
                .map(UserNote::getNote)
                .toList();

        if (notes.isEmpty()) {
            throw new NoNotesFoundException();
        }

        return notes;
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuário autenticado não encontrado."));
    }

    private UserNote findUserNoteByNoteId(Long noteId) {
        return userNoteRepository.findByNoteId(noteId)
                .orElseThrow(() -> new NoteNotFoundException(noteId));
    }
}
