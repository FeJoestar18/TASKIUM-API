package com.taskium.project.Domain.Interfaces.Repository;

import com.taskium.project.Domain.Entity.Status;

import java.util.Optional;

public interface IStatusRepository {
    Optional<Status> findById(Long id);
}

