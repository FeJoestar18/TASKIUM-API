package com.taskium.project.Infrastructure.Repository.Adapters;

import com.taskium.project.Domain.Entity.ParticipationStatus;
import com.taskium.project.Domain.Interfaces.Repository.IParticipationStatusRepository;
import com.taskium.project.Infrastructure.Repository.JPA.ParticipationStatusJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ParticipationStatusRepositoryImpl implements IParticipationStatusRepository {

    private final ParticipationStatusJpaRepository participationStatusJpaRepository;

    public ParticipationStatusRepositoryImpl(ParticipationStatusJpaRepository participationStatusJpaRepository) {
        this.participationStatusJpaRepository = participationStatusJpaRepository;
    }

    @Override
    public ParticipationStatus save(ParticipationStatus participationStatus) {
        return participationStatusJpaRepository.save(participationStatus);
    }

    @Override
    public Optional<ParticipationStatus> findById(Long id) {
        return participationStatusJpaRepository.findById(id);
    }

    @Override
    public List<ParticipationStatus> findAll() {
        return participationStatusJpaRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        participationStatusJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return participationStatusJpaRepository.existsById(id);
    }
}
