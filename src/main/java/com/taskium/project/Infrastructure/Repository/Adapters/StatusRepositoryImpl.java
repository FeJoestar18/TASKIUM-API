package com.taskium.project.Infrastructure.Repository.Adapters;
import com.taskium.project.Domain.Entity.Status;
import com.taskium.project.Domain.Interfaces.Repository.IStatusRepository;
import com.taskium.project.Infrastructure.Repository.JPA.StatusJpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public class StatusRepositoryImpl implements IStatusRepository {
    private final StatusJpaRepository jpaRepository;
    public StatusRepositoryImpl(StatusJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
    @Override
    public Optional<Status> findById(Long id) {
        return jpaRepository.findById(id);
    }
}
