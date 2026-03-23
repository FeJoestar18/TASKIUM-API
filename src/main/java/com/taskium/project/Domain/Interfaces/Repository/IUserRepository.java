package com.taskium.project.Domain.Interfaces.Repository;

import com.taskium.project.Domain.Entity.User;

import java.util.Optional;

public interface IUserRepository {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByPhoneNumber(String phone);
    Optional<User> findByEmail(String email);
    Optional<User>  findById(Long id);
    User save(User user);

}
