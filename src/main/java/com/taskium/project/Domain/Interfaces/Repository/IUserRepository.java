package com.taskium.project.Domain.Interfaces.Repository;

import com.taskium.project.Domain.Entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByPhoneNumber(String phone);
    boolean existsByEmailAndIdNot(String email, Long id);
    boolean existsByCpfAndIdNot(String cpf, Long id);
    boolean existsByPhoneNumberAndIdNot(String phone, Long id);
    Optional<User> findByEmail(String email);
    Optional<User>  findById(Long id);
    User save(User user);
    void deleteById(Long id);
    boolean existsById(Long id);
    List<User> findAll();
}
