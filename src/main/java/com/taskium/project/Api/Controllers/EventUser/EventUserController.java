package com.taskium.project.Api.Controllers.EventUser;

import com.taskium.project.Application.DTO.EventUser.AddUserToEventRequestDTO;
import com.taskium.project.Application.DTO.EventUser.EventUserResponseDTO;
import com.taskium.project.Application.DTO.EventUser.UpdateParticipationStatusDTO;
import com.taskium.project.Application.UseCases.EventUser.AddUserToEventUseCase;
import com.taskium.project.Application.UseCases.EventUser.GetEventsByUserUseCase;
import com.taskium.project.Application.UseCases.EventUser.GetUsersByEventUseCase;
import com.taskium.project.Application.UseCases.EventUser.RemoveUserFromEventUseCase;
import com.taskium.project.Application.UseCases.EventUser.UpdateEventUserParticipationStatusUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventUserController {

    private final AddUserToEventUseCase addUserToEventUseCase;
    private final RemoveUserFromEventUseCase removeUserFromEventUseCase;
    private final UpdateEventUserParticipationStatusUseCase updateEventUserParticipationStatusUseCase;
    private final GetUsersByEventUseCase getUsersByEventUseCase;
    private final GetEventsByUserUseCase getEventsByUserUseCase;

    public EventUserController(
            AddUserToEventUseCase addUserToEventUseCase,
            RemoveUserFromEventUseCase removeUserFromEventUseCase,
            UpdateEventUserParticipationStatusUseCase updateEventUserParticipationStatusUseCase,
            GetUsersByEventUseCase getUsersByEventUseCase,
            GetEventsByUserUseCase getEventsByUserUseCase
    ) {
        this.addUserToEventUseCase = addUserToEventUseCase;
        this.removeUserFromEventUseCase = removeUserFromEventUseCase;
        this.updateEventUserParticipationStatusUseCase = updateEventUserParticipationStatusUseCase;
        this.getUsersByEventUseCase = getUsersByEventUseCase;
        this.getEventsByUserUseCase = getEventsByUserUseCase;
    }

    @PostMapping("/events/{eventId}/users")
    @PreAuthorize("hasAuthority('JOIN_EVENT')")
    public ResponseEntity<EventUserResponseDTO> addUserToEvent(
            @PathVariable Long eventId,
            @Valid @RequestBody AddUserToEventRequestDTO dto,
            Authentication authentication
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(addUserToEventUseCase.execute(eventId, dto, authentication.getName()));
    }

    @DeleteMapping("/events/{eventId}/users/{userId}")
    @PreAuthorize("hasAuthority('MANAGE_EVENT_USERS')")
    public ResponseEntity<Void> removeUserFromEvent(
            @PathVariable Long eventId,
            @PathVariable Long userId,
            Authentication authentication
    ) {
        removeUserFromEventUseCase.execute(eventId, userId, authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/events/{eventId}/users/{userId}/status")
    @PreAuthorize("hasAuthority('MANAGE_EVENT_USERS')")
    public ResponseEntity<EventUserResponseDTO> updateParticipationStatus(
            @PathVariable Long eventId,
            @PathVariable Long userId,
            @Valid @RequestBody UpdateParticipationStatusDTO dto,
            Authentication authentication
    ) {
        return ResponseEntity.ok(updateEventUserParticipationStatusUseCase.execute(eventId, userId, dto, authentication.getName()));
    }

    @GetMapping("/events/{eventId}/users")
    @PreAuthorize("hasAuthority('VIEW_EVENT')")
    public ResponseEntity<List<EventUserResponseDTO>> getUsersByEvent(
            @PathVariable Long eventId,
            Authentication authentication
    ) {
        return ResponseEntity.ok(getUsersByEventUseCase.execute(eventId, authentication.getName()));
    }

    @GetMapping("/users/{userId}/events")
    @PreAuthorize("hasAuthority('VIEW_EVENT')")
    public ResponseEntity<List<EventUserResponseDTO>> getEventsByUser(
            @PathVariable Long userId,
            Authentication authentication
    ) {
        return ResponseEntity.ok(getEventsByUserUseCase.execute(userId, authentication.getName()));
    }
}
