package com.taskium.project.Application.DTO.User;

import com.taskium.project.Domain.Entity.User;
import com.taskium.project.Domain.Enums.RoleName;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserResponseDTO {

    private final Long id;
    private final String name;
    private final String email;
    private final RoleName role;
    private final LocalDateTime createdAt;

    public static UserResponseDTO from(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getUserDetail() != null && user.getUserDetail().getRole() != null
                        ? user.getUserDetail().getRole().getName()
                        : null)
                .createdAt(user.getCreatedAt())
                .build();
    }
}

