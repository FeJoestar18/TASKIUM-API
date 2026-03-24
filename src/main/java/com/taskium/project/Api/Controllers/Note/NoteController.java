package com.taskium.project.Api.Controllers.Note;

import com.taskium.project.Application.DTO.Note.CreateNoteRequestDTO;
import com.taskium.project.Application.DTO.Note.NoteListResponseDTO;
import com.taskium.project.Application.DTO.Note.NoteResponseDTO;
import com.taskium.project.Application.DTO.Note.UpdateNoteRequestDTO;
import com.taskium.project.Application.UseCases.Note.CreateNoteUseCase;
import com.taskium.project.Application.UseCases.Note.DeleteNoteByIdUseCase;
import com.taskium.project.Application.UseCases.Note.GetAllNotesUseCase;
import com.taskium.project.Application.UseCases.Note.GetNoteByIdUseCase;
import com.taskium.project.Application.UseCases.Note.GetNotesByUserUseCase;
import com.taskium.project.Application.UseCases.Note.UpdateNoteByIdUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {

    private final CreateNoteUseCase createNoteUseCase;
    private final UpdateNoteByIdUseCase updateNoteByIdUseCase;
    private final DeleteNoteByIdUseCase deleteNoteByIdUseCase;
    private final GetAllNotesUseCase getAllNotesUseCase;
    private final GetNoteByIdUseCase getNoteByIdUseCase;
    private final GetNotesByUserUseCase getNotesByUserUseCase;

    public NoteController(
            CreateNoteUseCase createNoteUseCase,
            UpdateNoteByIdUseCase updateNoteByIdUseCase,
            DeleteNoteByIdUseCase deleteNoteByIdUseCase,
            GetAllNotesUseCase getAllNotesUseCase,
            GetNoteByIdUseCase getNoteByIdUseCase,
            GetNotesByUserUseCase getNotesByUserUseCase
    ) {
        this.createNoteUseCase = createNoteUseCase;
        this.updateNoteByIdUseCase = updateNoteByIdUseCase;
        this.deleteNoteByIdUseCase = deleteNoteByIdUseCase;
        this.getAllNotesUseCase = getAllNotesUseCase;
        this.getNoteByIdUseCase = getNoteByIdUseCase;
        this.getNotesByUserUseCase = getNotesByUserUseCase;
    }

    @PostMapping("/notes")
    @PreAuthorize("hasAuthority('CREATE_NOTE')")
    public ResponseEntity<NoteResponseDTO> create(
            @Valid @RequestBody CreateNoteRequestDTO dto,
            Authentication authentication
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createNoteUseCase.execute(dto, authentication.getName()));
    }

    @PutMapping("/notes/{id}")
    @PreAuthorize("hasAuthority('UPDATE_NOTE')")
    public ResponseEntity<NoteResponseDTO> updateById(
            @PathVariable Long id,
            @Valid @RequestBody UpdateNoteRequestDTO dto,
            Authentication authentication
    ) {
        return ResponseEntity.ok(updateNoteByIdUseCase.execute(id, dto, authentication.getName()));
    }

    @DeleteMapping("/notes/{id}")
    @PreAuthorize("hasAuthority('DELETE_NOTE')")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long id,
            Authentication authentication
    ) {
        deleteNoteByIdUseCase.execute(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/notes")
    @PreAuthorize("hasAuthority('VIEW_NOTE')")
    public ResponseEntity<List<NoteListResponseDTO>> getAll(Authentication authentication) {
        return ResponseEntity.ok(getAllNotesUseCase.execute(authentication.getName()));
    }

    @GetMapping("/notes/{id}")
    @PreAuthorize("hasAuthority('VIEW_NOTE')")
    public ResponseEntity<NoteResponseDTO> getById(
            @PathVariable Long id,
            Authentication authentication
    ) {
        return ResponseEntity.ok(getNoteByIdUseCase.execute(id, authentication.getName()));
    }

    @GetMapping("/users/{userId}/notes")
    @PreAuthorize("hasAuthority('VIEW_NOTE')")
    public ResponseEntity<List<NoteListResponseDTO>> getByUserId(
            @PathVariable Long userId,
            Authentication authentication
    ) {
        return ResponseEntity.ok(getNotesByUserUseCase.execute(userId, authentication.getName()));
    }
}
