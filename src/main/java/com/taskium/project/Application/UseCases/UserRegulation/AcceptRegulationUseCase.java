package com.taskium.project.Application.UseCases.UserRegulation;

import com.taskium.project.Application.DTO.UserRegulation.UserRegulationResponseDTO;
import com.taskium.project.Domain.Interfaces.Services.UserRegulation.IUserRegulationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AcceptRegulationUseCase {

    private final IUserRegulationService userRegulationService;

    public AcceptRegulationUseCase(IUserRegulationService userRegulationService) {
        this.userRegulationService = userRegulationService;
    }

    @Transactional
    public UserRegulationResponseDTO execute(Long regulationId, String authenticatedEmail) {
        return UserRegulationResponseDTO.from(userRegulationService.acceptRegulation(regulationId, authenticatedEmail));
    }
}
