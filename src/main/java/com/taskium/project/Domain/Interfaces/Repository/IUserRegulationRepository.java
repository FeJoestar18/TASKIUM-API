package com.taskium.project.Domain.Interfaces.Repository;

import com.taskium.project.Domain.Entity.UserRegulation;

import java.util.List;

public interface IUserRegulationRepository {
    UserRegulation save(UserRegulation userRegulation);
    boolean existsByUserIdAndRegulationIdAndRegulationVersion(Long userId, Long regulationId, Integer regulationVersion);
    List<UserRegulation> findByUserId(Long userId);
}
