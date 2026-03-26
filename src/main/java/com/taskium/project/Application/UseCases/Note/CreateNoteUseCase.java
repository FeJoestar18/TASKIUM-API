package com.taskium.project.Application.UseCases.Note;

import com.taskium.project.Application.DTO.Note.CreateNoteRequestDTO;
import com.taskium.project.Application.DTO.Note.NoteResponseDTO;
import com.taskium.project.Domain.Common.Exceptions.User.UserNotFoundException;
import com.taskium.project.Domain.Entity.UserNote;
import com.taskium.project.Domain.Interfaces.Repository.INoteRepository;
import com.taskium.project.Domain.Interfaces.Repository.IUserNoteRepository;
import com.taskium.project.Domain.Interfaces.Repository.IUserRepository;
import com.taskium.project.Domain.Interfaces.Services.Note.INoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateNoteUseCase {

    private final INoteService noteService;
    private final INoteRepository noteRepository;
    private final IUserNoteRepository userNoteRepository;
    private final IUserRepository userRepository;

    public CreateNoteUseCase(
            INoteService noteService,
            INoteRepository noteRepository,
            IUserNoteRepository userNoteRepository,
            IUserRepository userRepository
    ) {
        this.noteService = noteService;
        this.noteRepository = noteRepository;
        this.userNoteRepository = userNoteRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public NoteResponseDTO execute(CreateNoteRequestDTO dto, String authenticatedEmail) {
        var user = userRepository.findByEmail(authenticatedEmail)
                .orElseThrow(() -> new UserNotFoundException("Usuário autenticado não encontrado."));

        var note = noteService.createNote(dto.getName(), dto.getDescription(), dto.getNote());
        note = noteRepository.save(note);

        var userNote = UserNote.builder()
                .user(user)
                .note(note)
                .build();
        userNoteRepository.save(userNote);

        return NoteResponseDTO.from(note);
    }
}
