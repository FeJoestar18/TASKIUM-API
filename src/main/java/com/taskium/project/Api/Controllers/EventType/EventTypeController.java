package com.taskium.project.Api.Controllers.EventType;

import com.taskium.project.Application.DTO.EventType.EventTypeRequestDTO;
import com.taskium.project.Application.DTO.EventType.EventTypeResponseDTO;
import com.taskium.project.Application.UseCases.EventType.CreateEventTypeUseCase;
import com.taskium.project.Application.UseCases.EventType.DeleteEventTypeUseCase;
import com.taskium.project.Application.UseCases.EventType.GetAllEventTypesUseCase;
import com.taskium.project.Application.UseCases.EventType.UpdateEventTypeUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event-types")
public class EventTypeController {

    private final CreateEventTypeUseCase createEventTypeUseCase;
    private final UpdateEventTypeUseCase updateEventTypeUseCase;
    private final DeleteEventTypeUseCase deleteEventTypeUseCase;
    private final GetAllEventTypesUseCase getAllEventTypesUseCase;

    public EventTypeController(
            CreateEventTypeUseCase createEventTypeUseCase,
            UpdateEventTypeUseCase updateEventTypeUseCase,
            DeleteEventTypeUseCase deleteEventTypeUseCase,
            GetAllEventTypesUseCase getAllEventTypesUseCase
    ) {
        this.createEventTypeUseCase = createEventTypeUseCase;
        this.updateEventTypeUseCase = updateEventTypeUseCase;
        this.deleteEventTypeUseCase = deleteEventTypeUseCase;
        this.getAllEventTypesUseCase = getAllEventTypesUseCase;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_EVENT_TYPE')")
    public ResponseEntity<EventTypeResponseDTO> create(@Valid @RequestBody EventTypeRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createEventTypeUseCase.execute(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_EVENT_TYPE')")
    public ResponseEntity<EventTypeResponseDTO> update(@PathVariable Long id, @Valid @RequestBody EventTypeRequestDTO dto) {
        return ResponseEntity.ok(updateEventTypeUseCase.execute(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_EVENT_TYPE')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteEventTypeUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_EVENT_TYPE')")
    public ResponseEntity<List<EventTypeResponseDTO>> getAll() {
        return ResponseEntity.ok(getAllEventTypesUseCase.execute());
    }
}
