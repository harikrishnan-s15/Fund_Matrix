package com.cog.fundmatrix.service;


import com.cog.fundmatrix.dto.user.*;


import com.cog.fundmatrix.domain.User;
import com.cog.fundmatrix.domain.enums.UserStatus;
import com.cog.fundmatrix.repository.UserRepository;
import com.cog.fundmatrix.security.CurrentUserService;
import com.cog.fundmatrix.security.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cog.fundmatrix.domain.enums.Role;

import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final CurrentUserService currentUser;
    private final Mapper mapper;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder, JwtService jwtService,
                       CurrentUserService currentUser, Mapper mapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.currentUser = currentUser;
        this.mapper = mapper;
    }

    @Transactional
    public AuthResponse register(RegisterRequest req) throws Exception{
        if (userRepository.existsByEmailIgnoreCase(req.email())) {
            throw new Exception("An account with this email already exists");
        }
        User user = User.builder()
                .name(req.name())
                .email(req.email().toLowerCase())
                .phone(req.phone())
                .role(Role.INVESTOR)
                .tokenVersion(1L)
                .status(UserStatus.ACTIVE)
                .password(passwordEncoder.encode(req.password()))
                .build();
        return issueToken(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest req) throws Exception {
        User user = userRepository.findByEmailIgnoreCase(req.email())
                .filter(u -> passwordEncoder.matches(req.password(), u.getPassword()))
                .orElseThrow(() -> new Exception("Invalid email or password"));
        if (user.getStatus() != UserStatus.ACTIVE) {
            throw new Exception("Account is " + user.getStatus());
        }
        return issueToken(user);
    }

    @Transactional(readOnly = true)
    public UserDto me() throws Exception {
        return mapper.toUserDto(currentUser.requireUser());
    }

    public AuthResponse refreshToken(HttpServletRequest req) throws Exception{
        String bearer = req.getHeader("Authorization");
        if(bearer !=null && bearer.startsWith("Bearer ") && SecurityContextHolder.getContext().getAuthentication() != null){
            String token = bearer.substring(7);
            if(jwtService.isValid(token)){
                User user = userRepository.findByEmailIgnoreCase(jwtService.extractUsername(token)).orElseThrow(()->new Exception("Not found"));
                return issueToken(user);
            }
        }
        return null;
    }

    private AuthResponse issueToken(User user) {
        String token = jwtService.generateToken(user);
        return AuthResponse.bearer(token, jwtService.expiryFromNow(), mapper.toUserDto(user));
    }

    public String logoutUser() throws Exception {
        User user = currentUser.requireUser();
        user.setTokenVersion(user.getTokenVersion() +1);
        userRepository.save(user);
        return "Logout Successfully";
    }

    public UserDto getUserById(UUID id) throws Exception {
        return mapper.toUserDto(userRepository.findById(id).orElseThrow(()->new Exception("User not found")));
    }

    public UserDto updateUserById(UUID id, RequestUpdateDto req) throws Exception {
        User user = userRepository.findById(id).orElseThrow(()->new Exception("User not found"));
        user.setName(req.name());
        user.setPhone(req.phone());
        return mapper.toUserDto(userRepository.save(user));
    }

    public UserDto updateUserStatus(UUID id, UpdateUserStatusRequest req) throws Exception{
        User user = userRepository.findById(id).orElseThrow(()->new Exception("User not found"));
        user.setStatus(req.status());
        return mapper.toUserDto(userRepository.save(user));
    }
}
