package com.taskium.project.Application.UseCases.ParticipationStatus;

import com.taskium.project.Application.DTO.ParticipationStatus.ParticipationStatusRequestDTO;
import com.taskium.project.Application.DTO.ParticipationStatus.ParticipationStatusResponseDTO;
import com.taskium.project.Domain.Interfaces.Services.ParticipationStatus.IParticipationStatusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateParticipationStatusUseCase {

    private final IParticipationStatusService participationStatusService;

    public UpdateParticipationStatusUseCase(IParticipationStatusService participationStatusService) {
        this.participationStatusService = participationStatusService;
    }

    @Transactional
    public ParticipationStatusResponseDTO execute(Long id, ParticipationStatusRequestDTO dto) {
        return ParticipationStatusResponseDTO.from(participationStatusService.update(id, dto));
    }
}
