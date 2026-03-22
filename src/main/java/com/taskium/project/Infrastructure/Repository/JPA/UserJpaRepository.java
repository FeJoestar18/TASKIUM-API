package com.taskium.project.Infrastructure.Repository.JPA;

import com.taskium.project.Domain.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    UserDetails findByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String email);
    void deleteByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByPhoneNumber(String phoneNumber);
}