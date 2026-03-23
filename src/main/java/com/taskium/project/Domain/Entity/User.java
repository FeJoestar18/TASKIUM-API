package com.taskium.project.Domain.Entity;

import com.taskium.project.Infrastructure.Persistence.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
@Entity
public class User extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "cpf", nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @OneToOne(mappedBy = "user")
    private com.taskium.project.Domain.Entity.UserDetails userDetail;
}
