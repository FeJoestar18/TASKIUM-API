package com.taskium.project.Domain.Entity;

import com.taskium.project.Infrastructure.Persistence.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "task_history")
@Entity
public class TaskHistory extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Column(name = "old_status_value", length = 50)
    private String oldStatusValue;

    @Column(name = "new_status_value", nullable = false, length = 50)
    private String newStatusValue;

    @Column(name = "reason", columnDefinition = "TEXT")
    private String reason;
}

