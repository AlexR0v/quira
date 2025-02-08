package ru.alexrov.quiraapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alexrov.quiraapi.entity.UserApp;
import ru.alexrov.quiraapi.mapper.CurrentUserMapper;
import ru.alexrov.quiraapi.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "Информация о пользователе")
public class UserController {
    private final UserService userService;
    private final CurrentUserMapper mapper;

    @GetMapping("/current")
    @Operation(summary = "Возвращает информацию о текущем пользователе")
    public ResponseEntity<?> currentUser() {
        UserApp user = userService.getCurrentUser();
        return ResponseEntity.ok(mapper.toCurrentUserResponse(user));
    }
}
