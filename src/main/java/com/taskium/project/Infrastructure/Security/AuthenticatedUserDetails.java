package com.taskium.project.Infrastructure.Security;

import com.taskium.project.Domain.Entity.Permission;
import com.taskium.project.Domain.Entity.Role;
import com.taskium.project.Domain.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

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
        Set<GrantedAuthority> authorities = new HashSet<>();

        if (user.getUserDetail() == null || user.getUserDetail().getRole() == null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return authorities;
        }

        Role role = user.getUserDetail().getRole();

        // Add role authority (e.g. ROLE_ADMIN, ROLE_USER, ROLE_MANAGER)
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().name()));

        // Add permission authorities from role's permissions
        if (role.getPermissions() != null) {
            for (Permission permission : role.getPermissions()) {
                authorities.add(new SimpleGrantedAuthority(permission.getName()));
            }
        }

        return authorities;
    }

    public List<String> getRoleNames() {
        if (user.getUserDetail() == null || user.getUserDetail().getRole() == null) {
            return List.of("USER");
        }
        return List.of(user.getUserDetail().getRole().getName().name());
    }

    public List<String> getPermissionNames() {
        if (user.getUserDetail() == null || user.getUserDetail().getRole() == null) {
            return List.of();
        }
        Role role = user.getUserDetail().getRole();
        if (role.getPermissions() == null) {
            return List.of();
        }
        return role.getPermissions().stream()
                .map(Permission::getName)
                .collect(Collectors.toList());
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

