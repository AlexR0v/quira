package ru.alexrov.quiraapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alexrov.quiraapi.auth.AuthenticationService;
import ru.alexrov.quiraapi.dto.JwtAuthenticationResponse;
import ru.alexrov.quiraapi.dto.SignInRequest;
import ru.alexrov.quiraapi.dto.SignUpRequest;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request, HttpServletResponse response) {

        Cookie cookie = new Cookie("refreshToken", "refreshToken12345");
        cookie.setHttpOnly(true); // Защита от XSS
        cookie.setSecure(false); // Только через HTTPS (в production)
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60); // 24 часа
        response.addCookie(cookie);

        return authenticationService.signIn(request);
    }
}
