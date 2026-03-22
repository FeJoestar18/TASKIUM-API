package com.taskium.project.Domain.Entity;

import com.taskium.project.Domain.Enums.RoleName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RoleName name;
}