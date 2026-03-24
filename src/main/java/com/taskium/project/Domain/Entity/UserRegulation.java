package com.taskium.project.Domain.Entity;

import com.taskium.project.Infrastructure.Persistence.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "user_regulations",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_user_regulation_version", columnNames = {"user_id", "regulation_id", "regulation_version"})
        }
)
@Entity
public class UserRegulation extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "regulation_id", nullable = false)
    private Regulation regulation;

    @Column(name = "regulation_version", nullable = false)
    private Integer regulationVersion;

    @Column(name = "accepted_at", nullable = false)
    private LocalDateTime acceptedAt;
}
