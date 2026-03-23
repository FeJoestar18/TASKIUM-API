package com.taskium.project.Infrastructure.Security;

import com.taskium.project.Domain.Entity.User;
import com.taskium.project.Domain.Enums.RoleName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class AuthenticatedUserDetails implements UserDetails {

    private final User user;

    public AuthenticatedUserDetails(User user) {
        this.user = Objects.requireNonNull(user, "user");
    }

    public User getDomainUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (user.getUserDetail() == null || user.getUserDetail().getRole() == null) {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }

        RoleName roleName = user.getUserDetail().getRole().getName();
        return switch (roleName) {
            case ADMIN -> List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
            case MANAGER -> List.of(
                    new SimpleGrantedAuthority("ROLE_MANAGER"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
            default -> List.of(new SimpleGrantedAuthority("ROLE_USER"));
        };
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

