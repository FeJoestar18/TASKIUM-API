package com.taskium.project.Api.Controllers.Permission;

import com.taskium.project.Application.DTO.Permission.CreatePermissionRequestDTO;
import com.taskium.project.Application.DTO.Permission.PermissionResponseDTO;
import com.taskium.project.Application.UseCases.Permission.CreatePermissionUseCase;
import com.taskium.project.Application.UseCases.Permission.GetAllPermissionsUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    private final CreatePermissionUseCase createPermissionUseCase;
    private final GetAllPermissionsUseCase getAllPermissionsUseCase;

    public PermissionController(
            CreatePermissionUseCase createPermissionUseCase,
            GetAllPermissionsUseCase getAllPermissionsUseCase
    ) {
        this.createPermissionUseCase = createPermissionUseCase;
        this.getAllPermissionsUseCase = getAllPermissionsUseCase;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('MANAGE_PERMISSIONS')")
    public ResponseEntity<PermissionResponseDTO> create(@Valid @RequestBody CreatePermissionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createPermissionUseCase.execute(dto));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('MANAGE_PERMISSIONS')")
    public ResponseEntity<List<PermissionResponseDTO>> getAll() {
        return ResponseEntity.ok(getAllPermissionsUseCase.execute());
    }
}

