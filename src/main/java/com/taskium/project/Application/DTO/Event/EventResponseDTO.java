package com.taskium.project.Application.DTO.Event;

import com.taskium.project.Domain.Entity.Event;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class EventResponseDTO {
    private final Long id;
    private final String name;
    private final String description;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final String location;
    private final Long createdById;
    private final String createdByName;
    private final Long eventTypeId;
    private final String eventTypeName;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static EventResponseDTO from(Event event) {
        return EventResponseDTO.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .location(event.getLocation())
                .createdById(event.getCreatedBy() != null ? event.getCreatedBy().getId() : null)
                .createdByName(event.getCreatedBy() != null ? event.getCreatedBy().getName() : null)
                .eventTypeId(event.getEventType() != null ? event.getEventType().getId() : null)
                .eventTypeName(event.getEventType() != null ? event.getEventType().getName() : null)
                .createdAt(event.getCreatedAt())
                .updatedAt(event.getUpdatedAt())
                .build();
    }
}
