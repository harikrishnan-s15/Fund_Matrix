package com.cog.fundmatrix.dto;

import com.cog.fundmatrix.domain.enums.Role;
import com.cog.fundmatrix.domain.enums.UserStatus;


public record RegisterRequest(
        String name,
        Role role,
        String email,
        String phone,
        UserStatus status,
        String password
) {
}
