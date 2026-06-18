package com.cog.fundmatrix.dto.user;

import jakarta.validation.constraints.*;

public record LoginRequest(
        @NotBlank @Email String email,
        @NotBlank String password
) {
}
