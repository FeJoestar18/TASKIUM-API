package com.taskium.project.Infrastructure.Repository.JPA;

import com.taskium.project.Domain.Entity.UserRegulation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRegulationJpaRepository extends JpaRepository<UserRegulation, Long> {
    boolean existsByUser_IdAndRegulation_IdAndRegulationVersion(Long userId, Long regulationId, Integer regulationVersion);
    List<UserRegulation> findByUser_Id(Long userId);
}
