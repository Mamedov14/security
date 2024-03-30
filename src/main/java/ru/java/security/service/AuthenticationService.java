package ru.java.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.java.security.dto.JwtAuthenticationResponse;
import ru.java.security.dto.SignInRequest;
import ru.java.security.dto.SignUpRequest;
import ru.java.security.entity.User;
import ru.java.security.enums.Role;
import ru.java.security.mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signUp(final SignUpRequest request) {
        final User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        userService.createUser(userMapper.toUserDto(user));
        final String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(final SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        final UserDetails user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());
        final String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}