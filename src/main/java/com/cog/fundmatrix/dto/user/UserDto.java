package com.cog.fundmatrix.dto.user;



import java.time.Instant;
import java.util.UUID;

import com.cog.fundmatrix.domain.enums.Role;
import com.cog.fundmatrix.domain.enums.UserStatus;

public record UserDto(
        UUID id,
        String name,
        String email,
        String phone,
        Role role,
        UserStatus status

) {
}
