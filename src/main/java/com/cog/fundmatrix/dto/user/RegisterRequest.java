package com.cog.fundmatrix.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record RegisterRequest(
        @NotBlank @Size(max = 120) String name,
        @NotBlank @Email String email,
        @Size(max = 20) String phone,
        @NotBlank @Size(min = 6, max = 72) String password
) {
}
