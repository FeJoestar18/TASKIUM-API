package com.taskium.project.Infrastructure.Config;

import com.taskium.project.Domain.Entity.Permission;
import com.taskium.project.Domain.Entity.Role;
import com.taskium.project.Domain.Enums.PermissionName;
import com.taskium.project.Domain.Enums.RoleName;
import com.taskium.project.Infrastructure.Repository.JPA.PermissionJpaRepository;
import com.taskium.project.Infrastructure.Repository.JPA.RoleJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRolesAndPermissions(RoleJpaRepository roleJpaRepository,
                                               PermissionJpaRepository permissionJpaRepository) {
        return args -> {

            // 1. Seed all permissions
            Map<String, Permission> permissionMap = new HashMap<>();
            for (PermissionName perm : PermissionName.values()) {
                Permission permission = permissionJpaRepository.findByName(perm.name())
                        .orElseGet(() -> permissionJpaRepository.save(
                                Permission.builder()
                                        .name(perm.name())
                                        .description(perm.name() + " permission")
                                        .build()
                        ));
                permissionMap.put(perm.name(), permission);
            }

            // 2. Define permission sets per role
            Set<Permission> allPermissions = new HashSet<>(permissionMap.values());

            Set<Permission> managerPermissions = Arrays.asList(
                    PermissionName.CREATE_TASK, PermissionName.VIEW_TASK, PermissionName.UPDATE_TASK, PermissionName.DELETE_TASK,
                    PermissionName.CREATE_EVENT, PermissionName.VIEW_EVENT, PermissionName.UPDATE_EVENT, PermissionName.DELETE_EVENT,
                    PermissionName.JOIN_EVENT, PermissionName.MANAGE_EVENT_USERS,
                    PermissionName.VIEW_USER, PermissionName.UPDATE_USER,
                    PermissionName.CREATE_NOTE, PermissionName.VIEW_NOTE, PermissionName.UPDATE_NOTE, PermissionName.DELETE_NOTE,
                    PermissionName.CREATE_COMMENT, PermissionName.VIEW_COMMENT, PermissionName.DELETE_COMMENT,
                    PermissionName.VIEW_REGULATION, PermissionName.ACCEPT_REGULATION,
                    PermissionName.VIEW_TASK_CATEGORY, PermissionName.CREATE_TASK_CATEGORY, PermissionName.UPDATE_TASK_CATEGORY,
                    PermissionName.VIEW_TASK_STATUS, PermissionName.CREATE_TASK_STATUS, PermissionName.UPDATE_TASK_STATUS,
                    PermissionName.VIEW_EVENT_TYPE, PermissionName.CREATE_EVENT_TYPE, PermissionName.UPDATE_EVENT_TYPE,
                    PermissionName.VIEW_PARTICIPATION_STATUS
            ).stream().map(p -> permissionMap.get(p.name())).collect(Collectors.toSet());

            Set<Permission> userPermissions = Arrays.asList(
                    PermissionName.VIEW_TASK, PermissionName.CREATE_TASK, PermissionName.UPDATE_TASK,
                    PermissionName.VIEW_EVENT, PermissionName.JOIN_EVENT,
                    PermissionName.VIEW_USER, PermissionName.UPDATE_USER,
                    PermissionName.CREATE_NOTE, PermissionName.VIEW_NOTE, PermissionName.UPDATE_NOTE, PermissionName.DELETE_NOTE,
                    PermissionName.CREATE_COMMENT, PermissionName.VIEW_COMMENT, PermissionName.DELETE_COMMENT,
                    PermissionName.VIEW_REGULATION, PermissionName.ACCEPT_REGULATION,
                    PermissionName.VIEW_TASK_CATEGORY, PermissionName.VIEW_TASK_STATUS,
                    PermissionName.VIEW_EVENT_TYPE, PermissionName.VIEW_PARTICIPATION_STATUS
            ).stream().map(p -> permissionMap.get(p.name())).collect(Collectors.toSet());

            // 3. Seed roles with permissions
            Map<RoleName, Set<Permission>> rolePermissionMap = Map.of(
                    RoleName.ADMIN, allPermissions,
                    RoleName.MANAGER, managerPermissions,
                    RoleName.USER, userPermissions
            );

            for (RoleName roleName : RoleName.values()) {
                Role role = roleJpaRepository.findByName(roleName)
                        .orElseGet(() -> roleJpaRepository.save(
                                Role.builder()
                                        .name(roleName)
                                        .description(roleName.name() + " role")
                                        .permissions(new HashSet<>())
                                        .build()
                        ));

                Set<Permission> targetPermissions = rolePermissionMap.getOrDefault(roleName, new HashSet<>());
                role.setPermissions(targetPermissions);
                roleJpaRepository.save(role);
            }
        };
    }
}