package com.taskium.project.Domain.Interfaces.Repository;

import com.taskium.project.Domain.Entity.User;
import com.taskium.project.Domain.Entity.UserDetails;

import java.util.Optional;

public interface IUserDetailsRepository {
    Optional<UserDetails> findByUserId(Long userId);
    UserDetails save(UserDetails userDetails);

}
