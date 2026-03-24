package com.taskium.project.Domain.Services.ParticipationStatus;

import com.taskium.project.Application.DTO.ParticipationStatus.ParticipationStatusRequestDTO;
import com.taskium.project.Domain.Common.Exceptions.Event.NoParticipationStatusesFoundException;
import com.taskium.project.Domain.Common.Exceptions.Event.ParticipationStatusNotFoundException;
import com.taskium.project.Domain.Entity.ParticipationStatus;
import com.taskium.project.Domain.Interfaces.Repository.IParticipationStatusRepository;
import com.taskium.project.Domain.Interfaces.Services.ParticipationStatus.IParticipationStatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipationStatusService implements IParticipationStatusService {

    private final IParticipationStatusRepository participationStatusRepository;

    public ParticipationStatusService(IParticipationStatusRepository participationStatusRepository) {
        this.participationStatusRepository = participationStatusRepository;
    }

    @Override
    public ParticipationStatus create(ParticipationStatusRequestDTO dto) {
        return participationStatusRepository.save(
                ParticipationStatus.builder()
                        .name(dto.getName())
                        .build()
        );
    }

    @Override
    public ParticipationStatus update(Long id, ParticipationStatusRequestDTO dto) {
        ParticipationStatus status = participationStatusRepository.findById(id)
                .orElseThrow(() -> new ParticipationStatusNotFoundException(id));
        status.setName(dto.getName());
        return participationStatusRepository.save(status);
    }

    @Override
    public void delete(Long id) {
        if (!participationStatusRepository.existsById(id)) {
            throw new ParticipationStatusNotFoundException(id);
        }
        participationStatusRepository.deleteById(id);
    }

    @Override
    public List<ParticipationStatus> getAll() {
        List<ParticipationStatus> statuses = participationStatusRepository.findAll();
        if (statuses.isEmpty()) {
            throw new NoParticipationStatusesFoundException();
        }
        return statuses;
    }
}
