package com.cog.fundmatrix.dto;

public record LoginRequest(
        String email,
        String password
) {
}
