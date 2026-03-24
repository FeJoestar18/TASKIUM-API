package com.taskium.project.Infrastructure.Repository.Adapters;

import com.taskium.project.Domain.Entity.UserDetails;
import com.taskium.project.Domain.Interfaces.Repository.IUserDetailsRepository;
import com.taskium.project.Infrastructure.Repository.JPA.UserDetailsJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDetailsRepositoryImpl implements IUserDetailsRepository {

    private final UserDetailsJpaRepository jpaRepository;

    public UserDetailsRepositoryImpl(UserDetailsJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<UserDetails> findByUserId(Long userId) {
        return jpaRepository.findByUserId(userId);
    }

    @Override
    public UserDetails save(UserDetails userDetails) {
        return jpaRepository.save(userDetails);
    }


}