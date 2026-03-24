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
        name = "user_notes",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_user_note_note_id", columnNames = {"note_id"})
        }
)
@Entity
public class UserNote extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "note_id", nullable = false)
    private Note note;
}
