package com.cog.fundmatrix.domain;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.cog.fundmatrix.domain.enums.Role;
import com.cog.fundmatrix.domain.enums.UserStatus;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, length = 120)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    /** BCrypt-hashed password; never serialized to the API. */
    @Column(name = "password_hash", nullable = false, length = 100)
    private String password;

    @Column(name = "tokenVersion", nullable = false, length = 100)
    private Long tokenVersion;

}