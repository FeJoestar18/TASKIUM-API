package com.taskium.project.Application.UseCases.UserRegulation;

import com.taskium.project.Application.DTO.UserRegulation.UserRegulationResponseDTO;
import com.taskium.project.Domain.Interfaces.Services.UserRegulation.IUserRegulationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAcceptedRegulationsByUserUseCase {

    private final IUserRegulationService userRegulationService;

    public GetAcceptedRegulationsByUserUseCase(IUserRegulationService userRegulationService) {
        this.userRegulationService = userRegulationService;
    }

    public List<UserRegulationResponseDTO> execute(Long userId, String authenticatedEmail) {
        return userRegulationService.getAcceptedRegulationsByUser(userId, authenticatedEmail).stream()
                .map(UserRegulationResponseDTO::from)
                .toList();
    }
}
