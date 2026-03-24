package com.taskium.project.Infrastructure.Repository.JPA;

import com.taskium.project.Domain.Entity.Regulation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegulationJpaRepository extends JpaRepository<Regulation, Long> {
}
