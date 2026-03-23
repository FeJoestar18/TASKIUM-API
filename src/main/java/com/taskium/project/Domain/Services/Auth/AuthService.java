package com.taskium.project.Domain.Services.Auth;

import com.taskium.project.Domain.Interfaces.Repository.IUserRepository;
import com.taskium.project.Infrastructure.Security.AuthenticatedUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    private final IUserRepository userRepository;

    public AuthService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByEmail(username)
                .map(AuthenticatedUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}