package com.taskium.project.Domain.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static com.taskium.project.Domain.Enums.RoleName.ADMIN;
import static com.taskium.project.Domain.Enums.RoleName.MANAGER;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
@Entity

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = true)
    private Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return switch (this.role.getName()) {
            case ADMIN -> List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );

            case MANAGER -> List.of(
                    new SimpleGrantedAuthority("ROLE_MANAGER"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );

            default -> List.of(
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        };
    }

    @Override
    public String getUsername() {
        return email;
    }
}
