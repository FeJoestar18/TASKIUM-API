package com.taskium.project.Api.Controllers.ParticipationStatus;

import com.taskium.project.Application.DTO.ParticipationStatus.ParticipationStatusRequestDTO;
import com.taskium.project.Application.DTO.ParticipationStatus.ParticipationStatusResponseDTO;
import com.taskium.project.Application.UseCases.ParticipationStatus.CreateParticipationStatusUseCase;
import com.taskium.project.Application.UseCases.ParticipationStatus.DeleteParticipationStatusUseCase;
import com.taskium.project.Application.UseCases.ParticipationStatus.GetAllParticipationStatusesUseCase;
import com.taskium.project.Application.UseCases.ParticipationStatus.UpdateParticipationStatusUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participation-status")
public class ParticipationStatusController {

    private final CreateParticipationStatusUseCase createParticipationStatusUseCase;
    private final UpdateParticipationStatusUseCase updateParticipationStatusUseCase;
    private final DeleteParticipationStatusUseCase deleteParticipationStatusUseCase;
    private final GetAllParticipationStatusesUseCase getAllParticipationStatusesUseCase;

    public ParticipationStatusController(
            CreateParticipationStatusUseCase createParticipationStatusUseCase,
            UpdateParticipationStatusUseCase updateParticipationStatusUseCase,
            DeleteParticipationStatusUseCase deleteParticipationStatusUseCase,
            GetAllParticipationStatusesUseCase getAllParticipationStatusesUseCase
    ) {
        this.createParticipationStatusUseCase = createParticipationStatusUseCase;
        this.updateParticipationStatusUseCase = updateParticipationStatusUseCase;
        this.deleteParticipationStatusUseCase = deleteParticipationStatusUseCase;
        this.getAllParticipationStatusesUseCase = getAllParticipationStatusesUseCase;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_PARTICIPATION_STATUS')")
    public ResponseEntity<ParticipationStatusResponseDTO> create(@Valid @RequestBody ParticipationStatusRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createParticipationStatusUseCase.execute(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PARTICIPATION_STATUS')")
    public ResponseEntity<ParticipationStatusResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ParticipationStatusRequestDTO dto) {
        return ResponseEntity.ok(updateParticipationStatusUseCase.execute(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_PARTICIPATION_STATUS')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteParticipationStatusUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_PARTICIPATION_STATUS')")
    public ResponseEntity<List<ParticipationStatusResponseDTO>> getAll() {
        return ResponseEntity.ok(getAllParticipationStatusesUseCase.execute());
    }
}
