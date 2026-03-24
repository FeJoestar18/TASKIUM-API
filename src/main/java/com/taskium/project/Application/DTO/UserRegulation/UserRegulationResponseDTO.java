package com.taskium.project.Application.DTO.UserRegulation;

import com.taskium.project.Domain.Entity.UserRegulation;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserRegulationResponseDTO {
    private final Long id;
    private final Long userId;
    private final Long regulationId;
    private final String regulationTitle;
    private final Integer regulationVersion;
    private final LocalDateTime acceptedAt;

    public static UserRegulationResponseDTO from(UserRegulation userRegulation) {
        return UserRegulationResponseDTO.builder()
                .id(userRegulation.getId())
                .userId(userRegulation.getUser().getId())
                .regulationId(userRegulation.getRegulation().getId())
                .regulationTitle(userRegulation.getRegulation().getTitle())
                .regulationVersion(userRegulation.getRegulationVersion())
                .acceptedAt(userRegulation.getAcceptedAt())
                .build();
    }
}
