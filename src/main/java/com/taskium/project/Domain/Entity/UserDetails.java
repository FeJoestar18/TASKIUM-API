package com.taskium.project.Domain.Entity;


import com.taskium.project.Infrastructure.Persistence.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users_details")
@Entity
public class UserDetails extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "reserved_phone_number", length = 15)
    private String reservedPhoneNumber;

    @Column(name = "reserved_email", length = 100)
    private String reservedEmail;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
}
