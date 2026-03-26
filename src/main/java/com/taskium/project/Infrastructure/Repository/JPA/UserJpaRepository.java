package com.taskium.project.Infrastructure.Repository.JPA;

import com.taskium.project.Domain.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    void deleteByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmailAndIdNot(String email, Long id);
    boolean existsByCpfAndIdNot(String cpf, Long id);
    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);
}