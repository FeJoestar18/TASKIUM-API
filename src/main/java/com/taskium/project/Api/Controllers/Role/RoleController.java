package com.taskium.project.Api.Controllers.Role;

import com.taskium.project.Application.DTO.Role.*;
import com.taskium.project.Application.UseCases.Role.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final CreateRoleUseCase createRoleUseCase;
    private final UpdateRoleUseCase updateRoleUseCase;
    private final DeleteRoleUseCase deleteRoleUseCase;
    private final GetAllRolesUseCase getAllRolesUseCase;
    private final GetRoleByIdUseCase getRoleByIdUseCase;
    private final AddPermissionsToRoleUseCase addPermissionsToRoleUseCase;
    private final RemovePermissionsFromRoleUseCase removePermissionsFromRoleUseCase;

    public RoleController(
            CreateRoleUseCase createRoleUseCase,
            UpdateRoleUseCase updateRoleUseCase,
            DeleteRoleUseCase deleteRoleUseCase,
            GetAllRolesUseCase getAllRolesUseCase,
            GetRoleByIdUseCase getRoleByIdUseCase,
            AddPermissionsToRoleUseCase addPermissionsToRoleUseCase,
            RemovePermissionsFromRoleUseCase removePermissionsFromRoleUseCase
    ) {
        this.createRoleUseCase = createRoleUseCase;
        this.updateRoleUseCase = updateRoleUseCase;
        this.deleteRoleUseCase = deleteRoleUseCase;
        this.getAllRolesUseCase = getAllRolesUseCase;
        this.getRoleByIdUseCase = getRoleByIdUseCase;
        this.addPermissionsToRoleUseCase = addPermissionsToRoleUseCase;
        this.removePermissionsFromRoleUseCase = removePermissionsFromRoleUseCase;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('MANAGE_ROLES')")
    public ResponseEntity<RoleResponseDTO> create(@Valid @RequestBody CreateRoleRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createRoleUseCase.execute(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGE_ROLES')")
    public ResponseEntity<RoleResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UpdateRoleRequestDTO dto) {
        return ResponseEntity.ok(updateRoleUseCase.execute(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGE_ROLES')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteRoleUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('MANAGE_ROLES')")
    public ResponseEntity<List<RoleResponseDTO>> getAll() {
        return ResponseEntity.ok(getAllRolesUseCase.execute());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGE_ROLES')")
    public ResponseEntity<RoleResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(getRoleByIdUseCase.execute(id));
    }

    @PostMapping("/{id}/permissions")
    @PreAuthorize("hasAuthority('MANAGE_ROLES')")
    public ResponseEntity<RoleResponseDTO> addPermissions(
            @PathVariable Long id,
            @Valid @RequestBody RolePermissionsRequestDTO dto
    ) {
        return ResponseEntity.ok(addPermissionsToRoleUseCase.execute(id, dto));
    }

    @DeleteMapping("/{id}/permissions")
    @PreAuthorize("hasAuthority('MANAGE_ROLES')")
    public ResponseEntity<RoleResponseDTO> removePermissions(
            @PathVariable Long id,
            @Valid @RequestBody RolePermissionsRequestDTO dto
    ) {
        return ResponseEntity.ok(removePermissionsFromRoleUseCase.execute(id, dto));
    }
}

