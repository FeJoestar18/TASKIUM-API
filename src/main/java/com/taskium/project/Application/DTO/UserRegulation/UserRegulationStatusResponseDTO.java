package com.taskium.project.Application.DTO.UserRegulation;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRegulationStatusResponseDTO {
    private final Long userId;
    private final Long regulationId;
    private final boolean accepted;
}
