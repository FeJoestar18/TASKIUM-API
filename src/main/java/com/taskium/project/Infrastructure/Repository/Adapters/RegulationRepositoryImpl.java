package com.taskium.project.Infrastructure.Repository.Adapters;

import com.taskium.project.Domain.Entity.Regulation;
import com.taskium.project.Domain.Interfaces.Repository.IRegulationRepository;
import com.taskium.project.Infrastructure.Repository.JPA.RegulationJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RegulationRepositoryImpl implements IRegulationRepository {

    private final RegulationJpaRepository regulationJpaRepository;

    public RegulationRepositoryImpl(RegulationJpaRepository regulationJpaRepository) {
        this.regulationJpaRepository = regulationJpaRepository;
    }

    @Override
    public Regulation save(Regulation regulation) {
        return regulationJpaRepository.save(regulation);
    }

    @Override
    public Optional<Regulation> findById(Long id) {
        return regulationJpaRepository.findById(id);
    }

    @Override
    public List<Regulation> findAll() {
        return regulationJpaRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        regulationJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return regulationJpaRepository.existsById(id);
    }
}
