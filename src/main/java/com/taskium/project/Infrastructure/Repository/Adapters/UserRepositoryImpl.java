package com.taskium.project.Infrastructure.Repository.Adapters;

import com.taskium.project.Domain.Entity.User;
import com.taskium.project.Domain.Interfaces.Repository.IUserRepository;
import com.taskium.project.Infrastructure.Repository.JPA.UserJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
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
    public boolean existsByEmailAndIdNot(String email, Long id) {
        return userJpaRepository.existsByEmailAndIdNot(email, id);
    }

    @Override
    public boolean existsByCpfAndIdNot(String cpf, Long id) {
        return userJpaRepository.existsByCpfAndIdNot(cpf, id);
    }

    @Override
    public boolean existsByPhoneNumberAndIdNot(String phone, Long id) {
        return userJpaRepository.existsByPhoneNumberAndIdNot(phone, id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public void deleteById(Long id) { userJpaRepository.deleteById(id); }

    @Override
    public boolean existsById(Long id) {
        return userJpaRepository.existsById(id);
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll();
    }
}
