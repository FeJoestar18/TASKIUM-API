package com.taskium.project.Application.UseCases.Note;

import com.taskium.project.Domain.Interfaces.Repository.INoteRepository;
import com.taskium.project.Domain.Interfaces.Repository.IUserNoteRepository;
import com.taskium.project.Domain.Interfaces.Services.Note.INoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteNoteByIdUseCase {

    private final INoteService noteService;
    private final INoteRepository noteRepository;
    private final IUserNoteRepository userNoteRepository;

    public DeleteNoteByIdUseCase(
            INoteService noteService,
            INoteRepository noteRepository,
            IUserNoteRepository userNoteRepository
    ) {
        this.noteService = noteService;
        this.noteRepository = noteRepository;
        this.userNoteRepository = userNoteRepository;
    }

    @Transactional
    public void execute(Long id, String authenticatedEmail) {
        noteService.validateNoteDeletion(id, authenticatedEmail);
        userNoteRepository.deleteByNoteId(id);
        noteRepository.deleteById(id);
    }
}
