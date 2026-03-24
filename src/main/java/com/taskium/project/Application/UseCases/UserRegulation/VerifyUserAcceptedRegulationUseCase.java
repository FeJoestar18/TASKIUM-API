package com.taskium.project.Application.UseCases.UserRegulation;

import com.taskium.project.Application.DTO.UserRegulation.UserRegulationStatusResponseDTO;
import com.taskium.project.Domain.Interfaces.Services.UserRegulation.IUserRegulationService;
import org.springframework.stereotype.Service;

@Service
public class VerifyUserAcceptedRegulationUseCase {

    private final IUserRegulationService userRegulationService;

    public VerifyUserAcceptedRegulationUseCase(IUserRegulationService userRegulationService) {
        this.userRegulationService = userRegulationService;
    }

    public UserRegulationStatusResponseDTO execute(Long userId, Long regulationId, String authenticatedEmail) {
        boolean accepted = userRegulationService.hasAcceptedRegulation(userId, regulationId, authenticatedEmail);
        return UserRegulationStatusResponseDTO.builder()
                .userId(userId)
                .regulationId(regulationId)
                .accepted(accepted)
                .build();
    }
}
