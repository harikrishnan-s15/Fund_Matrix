package com.cog.fundmatrix.dto;

import com.cog.fundmatrix.domain.enums.Role;
import com.cog.fundmatrix.domain.enums.UserStatus;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email,
        String phone,
        Role role,
        UserStatus status
) {
}
