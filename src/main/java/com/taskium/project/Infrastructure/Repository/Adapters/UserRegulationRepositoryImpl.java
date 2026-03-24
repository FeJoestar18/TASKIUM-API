package com.taskium.project.Infrastructure.Repository.Adapters;

import com.taskium.project.Domain.Entity.UserRegulation;
import com.taskium.project.Domain.Interfaces.Repository.IUserRegulationRepository;
import com.taskium.project.Infrastructure.Repository.JPA.UserRegulationJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRegulationRepositoryImpl implements IUserRegulationRepository {

    private final UserRegulationJpaRepository userRegulationJpaRepository;

    public UserRegulationRepositoryImpl(UserRegulationJpaRepository userRegulationJpaRepository) {
        this.userRegulationJpaRepository = userRegulationJpaRepository;
    }

    @Override
    public UserRegulation save(UserRegulation userRegulation) {
        return userRegulationJpaRepository.save(userRegulation);
    }

    @Override
    public boolean existsByUserIdAndRegulationIdAndRegulationVersion(Long userId, Long regulationId, Integer regulationVersion) {
        return userRegulationJpaRepository.existsByUser_IdAndRegulation_IdAndRegulationVersion(userId, regulationId, regulationVersion);
    }

    @Override
    public List<UserRegulation> findByUserId(Long userId) {
        return userRegulationJpaRepository.findByUser_Id(userId);
    }
}
