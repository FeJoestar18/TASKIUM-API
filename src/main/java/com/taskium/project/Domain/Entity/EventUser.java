package com.taskium.project.Domain.Entity;

import com.taskium.project.Infrastructure.Persistence.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "event_users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_event_user", columnNames = {"user_id", "event_id"})
        },
        indexes = {
                @Index(name = "idx_event_users_event_id", columnList = "event_id"),
                @Index(name = "idx_event_users_user_id", columnList = "user_id")
        }
)
@Entity
public class EventUser extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "participation_status_id", nullable = false)
    private ParticipationStatus participationStatus;
}
