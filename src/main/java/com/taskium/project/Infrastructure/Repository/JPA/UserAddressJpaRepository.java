package com.taskium.project.Infrastructure.Repository.JPA;

import com.taskium.project.Domain.Entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAddressJpaRepository extends JpaRepository<UserAddress, Long> {
    List<UserAddress> findByUserId(Long userId);
}

