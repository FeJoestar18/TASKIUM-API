package com.taskium.project.Domain.Interfaces.Repository;

import com.taskium.project.Domain.Entity.ParticipationStatus;

import java.util.List;
import java.util.Optional;

public interface IParticipationStatusRepository {
    ParticipationStatus save(ParticipationStatus participationStatus);
    Optional<ParticipationStatus> findById(Long id);
    List<ParticipationStatus> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
