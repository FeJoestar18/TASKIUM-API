package com.taskium.project.Application.DTO.Regulation;

import com.taskium.project.Domain.Entity.Regulation;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class RegulationResponseDTO {
    private final Long id;
    private final String title;
    private final String content;
    private final Integer version;
    private final Boolean isActive;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static RegulationResponseDTO from(Regulation regulation) {
        return RegulationResponseDTO.builder()
                .id(regulation.getId())
                .title(regulation.getTitle())
                .content(regulation.getContent())
                .version(regulation.getVersion())
                .isActive(regulation.getIsActive())
                .createdAt(regulation.getCreatedAt())
                .updatedAt(regulation.getUpdatedAt())
                .build();
    }
}
