package com.taskium.project.Infrastructure.Repository.Adapters;

import com.taskium.project.Domain.Entity.User;
import com.taskium.project.Domain.Interfaces.Repository.IUserRepository;
import com.taskium.project.Infrastructure.Repository.JPA.UserJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return userJpaRepository.existsByCpf(cpf);
    }

    @Override
    public boolean existsByPhoneNumber(String phone) {
        return userJpaRepository.existsByPhoneNumber(phone);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }
}
