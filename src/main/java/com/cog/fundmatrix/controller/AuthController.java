package com.cog.fundmatrix.controller;

import com.cog.fundmatrix.dto.user.*;
import com.cog.fundmatrix.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/auth/users")
@Tag(name = "Authentication", description = "Registration, login and current-user lookup")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) throws Exception {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) throws Exception{
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me() throws Exception{
        return ResponseEntity.ok(authService.me());
    }

    @GetMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(HttpServletRequest req) throws Exception{
        return ResponseEntity.ok(authService.refreshToken(req));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() throws Exception{
        return ResponseEntity.ok(authService.logoutUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") UUID id) throws Exception{
        return ResponseEntity.ok(authService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUserById(@PathVariable("id") UUID id, @RequestBody RequestUpdateDto req) throws Exception{
        return ResponseEntity.ok(authService.updateUserById(id, req));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<UserDto> updateUserStatus(@PathVariable("id") UUID id, @RequestBody UpdateUserStatusRequest req) throws Exception{
        return ResponseEntity.ok(authService.updateUserStatus(id, req));
    }


}