package com.taskium.project.Application.DTO.ParticipationStatus;

import com.taskium.project.Domain.Entity.ParticipationStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ParticipationStatusResponseDTO {
    private final Long id;
    private final String name;

    public static ParticipationStatusResponseDTO from(ParticipationStatus participationStatus) {
        return ParticipationStatusResponseDTO.builder()
                .id(participationStatus.getId())
                .name(participationStatus.getName())
                .build();
    }
}
