package com.taskium.project.Application.DTO.Role;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateRoleRequestDTO {

    private String description;

    private Set<Long> permissionIds;
}

