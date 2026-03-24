package com.taskium.project.Api.Controllers.UserRegulation;

import com.taskium.project.Application.DTO.UserRegulation.UserRegulationResponseDTO;
import com.taskium.project.Application.DTO.UserRegulation.UserRegulationStatusResponseDTO;
import com.taskium.project.Application.UseCases.UserRegulation.GetAcceptedRegulationsByUserUseCase;
import com.taskium.project.Application.UseCases.UserRegulation.VerifyUserAcceptedRegulationUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRegulationController {

    private final GetAcceptedRegulationsByUserUseCase getAcceptedRegulationsByUserUseCase;
    private final VerifyUserAcceptedRegulationUseCase verifyUserAcceptedRegulationUseCase;

    public UserRegulationController(
            GetAcceptedRegulationsByUserUseCase getAcceptedRegulationsByUserUseCase,
            VerifyUserAcceptedRegulationUseCase verifyUserAcceptedRegulationUseCase
    ) {
        this.getAcceptedRegulationsByUserUseCase = getAcceptedRegulationsByUserUseCase;
        this.verifyUserAcceptedRegulationUseCase = verifyUserAcceptedRegulationUseCase;
    }

    @GetMapping("/{userId}/regulations")
    @PreAuthorize("hasAuthority('VIEW_REGULATION')")
    public ResponseEntity<List<UserRegulationResponseDTO>> getAcceptedByUser(
            @PathVariable Long userId,
            Authentication authentication
    ) {
        return ResponseEntity.ok(getAcceptedRegulationsByUserUseCase.execute(userId, authentication.getName()));
    }

    @GetMapping("/{userId}/regulations/{regulationId}/status")
    @PreAuthorize("hasAuthority('VIEW_REGULATION')")
    public ResponseEntity<UserRegulationStatusResponseDTO> hasAccepted(
            @PathVariable Long userId,
            @PathVariable Long regulationId,
            Authentication authentication
    ) {
        return ResponseEntity.ok(verifyUserAcceptedRegulationUseCase.execute(userId, regulationId, authentication.getName()));
    }
}
