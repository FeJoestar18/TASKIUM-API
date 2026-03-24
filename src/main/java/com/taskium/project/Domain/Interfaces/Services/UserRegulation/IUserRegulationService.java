package com.taskium.project.Domain.Interfaces.Services.UserRegulation;

import com.taskium.project.Domain.Entity.UserRegulation;

import java.util.List;

public interface IUserRegulationService {
    UserRegulation acceptRegulation(Long regulationId, String authenticatedEmail);
    List<UserRegulation> getAcceptedRegulationsByUser(Long userId, String authenticatedEmail);
    boolean hasAcceptedRegulation(Long userId, Long regulationId, String authenticatedEmail);
}
