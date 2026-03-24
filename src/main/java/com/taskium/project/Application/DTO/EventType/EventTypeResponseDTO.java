package com.taskium.project.Application.DTO.EventType;

import com.taskium.project.Domain.Entity.EventType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventTypeResponseDTO {
    private final Long id;
    private final String name;
    private final String description;

    public static EventTypeResponseDTO from(EventType eventType) {
        return EventTypeResponseDTO.builder()
                .id(eventType.getId())
                .name(eventType.getName())
                .description(eventType.getDescription())
                .build();
    }
}
