package ru.alexrov.quiraapi.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.alexrov.quiraapi.dto.JwtAuthenticationResponse;
import ru.alexrov.quiraapi.dto.SignInRequest;
import ru.alexrov.quiraapi.dto.SignUpRequest;
import ru.alexrov.quiraapi.entity.UserApp;
import ru.alexrov.quiraapi.entity.enums.Role;
import ru.alexrov.quiraapi.service.RefreshTokenService;
import ru.alexrov.quiraapi.service.UserService;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    public JwtAuthenticationResponse signUp(SignUpRequest request) {

        var user = UserApp.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userService.create(user);
        this.refreshTokenService.generateRefreshToken(user);
        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getEmail());
        UserApp userApp = userService.getByEmail(request.getEmail());
        this.refreshTokenService.generateRefreshToken(userApp);
        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
