package com.taskium.project.Application.DTO.EventUser;

import com.taskium.project.Domain.Entity.EventUser;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventUserResponseDTO {
    private final Long id;
    private final Long userId;
    private final String userName;
    private final Long eventId;
    private final String eventName;
    private final Long participationStatusId;
    private final String participationStatusName;

    public static EventUserResponseDTO from(EventUser eventUser) {
        return EventUserResponseDTO.builder()
                .id(eventUser.getId())
                .userId(eventUser.getUser() != null ? eventUser.getUser().getId() : null)
                .userName(eventUser.getUser() != null ? eventUser.getUser().getName() : null)
                .eventId(eventUser.getEvent() != null ? eventUser.getEvent().getId() : null)
                .eventName(eventUser.getEvent() != null ? eventUser.getEvent().getName() : null)
                .participationStatusId(eventUser.getParticipationStatus() != null ? eventUser.getParticipationStatus().getId() : null)
                .participationStatusName(eventUser.getParticipationStatus() != null ? eventUser.getParticipationStatus().getName() : null)
                .build();
    }
}
