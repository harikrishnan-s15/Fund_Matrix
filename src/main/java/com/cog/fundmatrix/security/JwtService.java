package com.cog.fundmatrix.security;

import com.cog.fundmatrix.domain.User;
import com.cog.fundmatrix.domain.enums.Role;
import com.cog.fundmatrix.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

/** Issues and validates HMAC-signed JSON Web Tokens for stateless authentication. */
@Service
public class JwtService {

    private final SecretKey signingKey;
    private final long expirationMs;
    private final UserRepository userRepository;

    public JwtService(
            @Value("${fundmatrix.security.jwt.secret}") String secret,
            @Value("${fundmatrix.security.jwt.expiration-ms}") long expirationMs,
            UserRepository userRepository) {
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMs = expirationMs;
        this.userRepository = userRepository;
    }

    public Instant expiryFromNow() {
        return Instant.now().plusMillis(expirationMs);
    }

    public String generateToken(User user) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("uid", user.getId())
                .claim("role", user.getRole().name())
                .claim("tokenVersion", user.getTokenVersion())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(expirationMs)))
                .signWith(signingKey)
                .compact();
    }

    public String extractUsername(String token) {
        return parse(token).getSubject();
    }

    public UUID extractId(String token){
        return parse(token).get("uid", UUID.class);
    }

    public Role extarctRole(String token){
        return parse(token).get("role", Role.class);
    }

    public boolean isValid(String token) {
        try {
            Claims c = parse(token);
            User user = userRepository.findByEmailIgnoreCase(extractUsername(token)).orElseThrow(()-> new Exception("Not found"));
            return (c.getExpiration().after(new Date()) && (c.get("tokenVersion", Long.class) == user.getTokenVersion()));
        } catch (Exception ex) {
            return false;
        }
    }

    private Claims parse(String token) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
