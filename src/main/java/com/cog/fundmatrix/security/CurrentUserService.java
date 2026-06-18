package com.cog.fundmatrix.security;


import com.cog.fundmatrix.domain.User;
import com.cog.fundmatrix.domain.enums.Role;
import com.cog.fundmatrix.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/** Convenience accessor for the authenticated user (the JWT filter stores the User as principal). */
@Service
public class CurrentUserService {

    private final UserRepository userRepository;

    public CurrentUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User principal() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof User user)) {
            throw new IllegalStateException("No authenticated user in context");
        }
        return user;
    }

    public UUID getId() {
        return principal().getId();
    }

    public Role getRole() {
        return principal().getRole();
    }

    public boolean hasRole(Role role) {
        return getRole() == role;
    }

    /** Re-loads the User in the current transaction (managed instance). */
    public User requireUser()  throws Exception{
        UUID id = getId();
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found"));
    }
}
