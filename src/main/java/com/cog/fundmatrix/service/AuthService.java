package com.cog.fundmatrix.service;


import com.cog.fundmatrix.dto.LoginRequest;
import com.cog.fundmatrix.dto.RegisterRequest;
import com.cog.fundmatrix.dto.UserResponse;


import com.cog.fundmatrix.domain.User;
import com.cog.fundmatrix.domain.enums.UserStatus;
import com.cog.fundmatrix.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse register(RegisterRequest req) throws Exception{
        if (userRepository.existsByEmailIgnoreCase(req.email())) {
            throw new Exception("An account with this email already exists");
        }
        User user = User.builder()
                .name(req.name())
                .role(req.role())
                .email(req.email())
                .phone(req.phone())
                .status(req.status())
                .password(passwordEncoder.encode(req.password()))
                .build();
        return toUser(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public UserResponse login(LoginRequest req) throws Exception{
        User user = userRepository.findByEmailIgnoreCase(req.email())
                .filter(u -> passwordEncoder.matches(req.password(), u.getPassword()))
                .orElseThrow(() -> new Exception("Invalid email or password"));
        if (user.getStatus() != UserStatus.ACTIVE) {
            throw new Exception("Account is " + user.getStatus());
        }
        return toUser(user);
    }

    public UserResponse toUser(User user){
            return new UserResponse(user.getId(),user.getName(),user.getEmail(),user.getPhone(),user.getRole(),user.getStatus());
    }
}
