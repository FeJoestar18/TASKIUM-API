package com.taskium.project.Domain.Interfaces.Services.ParticipationStatus;

import com.taskium.project.Application.DTO.ParticipationStatus.ParticipationStatusRequestDTO;
import com.taskium.project.Domain.Entity.ParticipationStatus;

import java.util.List;

public interface IParticipationStatusService {
    ParticipationStatus create(ParticipationStatusRequestDTO dto);
    ParticipationStatus update(Long id, ParticipationStatusRequestDTO dto);
    void delete(Long id);
    List<ParticipationStatus> getAll();
}
