package com.taskium.project.Domain.Interfaces.Repository;

import com.taskium.project.Domain.Entity.Regulation;

import java.util.List;
import java.util.Optional;

public interface IRegulationRepository {
    Regulation save(Regulation regulation);
    Optional<Regulation> findById(Long id);
    List<Regulation> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
