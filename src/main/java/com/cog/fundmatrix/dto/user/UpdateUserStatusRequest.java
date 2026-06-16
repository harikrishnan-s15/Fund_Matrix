package com.cog.fundmatrix.dto.user;

import com.cog.fundmatrix.domain.enums.UserStatus;

public record UpdateUserStatusRequest( UserStatus status) {
}
