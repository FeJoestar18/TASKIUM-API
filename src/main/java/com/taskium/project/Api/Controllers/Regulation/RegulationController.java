package com.taskium.project.Api.Controllers.Regulation;

import com.taskium.project.Application.DTO.Regulation.RegulationRequestDTO;
import com.taskium.project.Application.DTO.Regulation.RegulationResponseDTO;
import com.taskium.project.Application.DTO.UserRegulation.UserRegulationResponseDTO;
import com.taskium.project.Application.UseCases.Regulation.CreateRegulationUseCase;
import com.taskium.project.Application.UseCases.Regulation.DeleteRegulationByIdUseCase;
import com.taskium.project.Application.UseCases.Regulation.GetAllRegulationsUseCase;
import com.taskium.project.Application.UseCases.Regulation.GetRegulationByIdUseCase;
import com.taskium.project.Application.UseCases.Regulation.UpdateRegulationByIdUseCase;
import com.taskium.project.Application.UseCases.UserRegulation.AcceptRegulationUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regulations")
public class RegulationController {

    private final CreateRegulationUseCase createRegulationUseCase;
    private final UpdateRegulationByIdUseCase updateRegulationByIdUseCase;
    private final DeleteRegulationByIdUseCase deleteRegulationByIdUseCase;
    private final GetAllRegulationsUseCase getAllRegulationsUseCase;
    private final GetRegulationByIdUseCase getRegulationByIdUseCase;
    private final AcceptRegulationUseCase acceptRegulationUseCase;

    public RegulationController(
            CreateRegulationUseCase createRegulationUseCase,
            UpdateRegulationByIdUseCase updateRegulationByIdUseCase,
            DeleteRegulationByIdUseCase deleteRegulationByIdUseCase,
            GetAllRegulationsUseCase getAllRegulationsUseCase,
            GetRegulationByIdUseCase getRegulationByIdUseCase,
            AcceptRegulationUseCase acceptRegulationUseCase
    ) {
        this.createRegulationUseCase = createRegulationUseCase;
        this.updateRegulationByIdUseCase = updateRegulationByIdUseCase;
        this.deleteRegulationByIdUseCase = deleteRegulationByIdUseCase;
        this.getAllRegulationsUseCase = getAllRegulationsUseCase;
        this.getRegulationByIdUseCase = getRegulationByIdUseCase;
        this.acceptRegulationUseCase = acceptRegulationUseCase;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_REGULATION')")
    public ResponseEntity<RegulationResponseDTO> create(@Valid @RequestBody RegulationRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createRegulationUseCase.execute(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_REGULATION')")
    public ResponseEntity<RegulationResponseDTO> update(@PathVariable Long id, @Valid @RequestBody RegulationRequestDTO dto) {
        return ResponseEntity.ok(updateRegulationByIdUseCase.execute(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_REGULATION')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteRegulationByIdUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_REGULATION')")
    public ResponseEntity<List<RegulationResponseDTO>> getAll() {
        return ResponseEntity.ok(getAllRegulationsUseCase.execute());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VIEW_REGULATION')")
    public ResponseEntity<RegulationResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(getRegulationByIdUseCase.execute(id));
    }

    @PostMapping("/{id}/accept")
    @PreAuthorize("hasAuthority('ACCEPT_REGULATION')")
    public ResponseEntity<UserRegulationResponseDTO> accept(@PathVariable Long id, Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED).body(acceptRegulationUseCase.execute(id, authentication.getName()));
    }
}
