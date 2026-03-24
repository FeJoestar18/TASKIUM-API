package com.taskium.project.Application.UseCases.ParticipationStatus;

import com.taskium.project.Application.DTO.ParticipationStatus.ParticipationStatusResponseDTO;
import com.taskium.project.Domain.Interfaces.Services.ParticipationStatus.IParticipationStatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllParticipationStatusesUseCase {

    private final IParticipationStatusService participationStatusService;

    public GetAllParticipationStatusesUseCase(IParticipationStatusService participationStatusService) {
        this.participationStatusService = participationStatusService;
    }

    public List<ParticipationStatusResponseDTO> execute() {
        return participationStatusService.getAll()
                .stream()
                .map(ParticipationStatusResponseDTO::from)
                .toList();
    }
}
