package com.taskium.project.Infrastructure.Config;

import com.taskium.project.Domain.Entity.Role;
import com.taskium.project.Domain.Enums.RoleName;
import com.taskium.project.Infrastructure.Repository.JPA.RoleJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRoles(RoleJpaRepository roleJpaRepository) {
        return args -> {

            for (RoleName roleName : RoleName.values()) {

                roleJpaRepository.findByName(roleName)
                        .orElseGet(() -> roleJpaRepository.save(
                                Role.builder()
                                        .name(roleName)
                                        .description(roleName.name() + " role")
                                        .build()
                        ));
            }

        };
    }
}