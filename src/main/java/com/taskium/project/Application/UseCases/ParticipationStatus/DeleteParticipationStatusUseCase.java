package com.taskium.project.Application.UseCases.ParticipationStatus;

import com.taskium.project.Domain.Interfaces.Services.ParticipationStatus.IParticipationStatusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteParticipationStatusUseCase {

    private final IParticipationStatusService participationStatusService;

    public DeleteParticipationStatusUseCase(IParticipationStatusService participationStatusService) {
        this.participationStatusService = participationStatusService;
    }

    @Transactional
    public void execute(Long id) {
        participationStatusService.delete(id);
    }
}
