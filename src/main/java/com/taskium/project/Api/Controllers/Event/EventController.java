package com.taskium.project.Api.Controllers.Event;

import com.taskium.project.Application.DTO.Event.CreateEventRequestDTO;
import com.taskium.project.Application.DTO.Event.EventResponseDTO;
import com.taskium.project.Application.DTO.Event.UpdateEventRequestDTO;
import com.taskium.project.Application.UseCases.Event.CreateEventUseCase;
import com.taskium.project.Application.UseCases.Event.DeleteEventUseCase;
import com.taskium.project.Application.UseCases.Event.GetAllEventsUseCase;
import com.taskium.project.Application.UseCases.Event.GetEventByIdUseCase;
import com.taskium.project.Application.UseCases.Event.UpdateEventUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final CreateEventUseCase createEventUseCase;
    private final UpdateEventUseCase updateEventUseCase;
    private final DeleteEventUseCase deleteEventUseCase;
    private final GetAllEventsUseCase getAllEventsUseCase;
    private final GetEventByIdUseCase getEventByIdUseCase;

    public EventController(
            CreateEventUseCase createEventUseCase,
            UpdateEventUseCase updateEventUseCase,
            DeleteEventUseCase deleteEventUseCase,
            GetAllEventsUseCase getAllEventsUseCase,
            GetEventByIdUseCase getEventByIdUseCase
    ) {
        this.createEventUseCase = createEventUseCase;
        this.updateEventUseCase = updateEventUseCase;
        this.deleteEventUseCase = deleteEventUseCase;
        this.getAllEventsUseCase = getAllEventsUseCase;
        this.getEventByIdUseCase = getEventByIdUseCase;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_EVENT')")
    public ResponseEntity<EventResponseDTO> create(@Valid @RequestBody CreateEventRequestDTO dto, Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createEventUseCase.execute(dto, authentication.getName()));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_EVENT')")
    public ResponseEntity<EventResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UpdateEventRequestDTO dto, Authentication authentication) {
        return ResponseEntity.ok(updateEventUseCase.execute(id, dto, authentication.getName()));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_EVENT')")
    public ResponseEntity<Void> delete(@PathVariable Long id, Authentication authentication) {
        deleteEventUseCase.execute(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_EVENT')")
    public ResponseEntity<List<EventResponseDTO>> getAll() {
        return ResponseEntity.ok(getAllEventsUseCase.execute());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VIEW_EVENT')")
    public ResponseEntity<EventResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(getEventByIdUseCase.execute(id));
    }
}
