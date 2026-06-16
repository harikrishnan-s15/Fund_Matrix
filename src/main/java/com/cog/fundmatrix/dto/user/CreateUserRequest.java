package com.cog.fundmatrix.dto.user;


import com.cog.fundmatrix.domain.enums.Role;


/** Admin-driven user provisioning for staff and distributor accounts. */
public record CreateUserRequest(
        String name,
         String email,
         String phone,
         Role role,
         String password
) {
}
