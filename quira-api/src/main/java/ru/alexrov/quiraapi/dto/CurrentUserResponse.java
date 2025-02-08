package ru.alexrov.quiraapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.alexrov.quiraapi.entity.enums.Role;

@Data
@Schema(description = "Запрос на текущего пользователя")
public class CurrentUserResponse {
    private String email;
    private String username;
    private Long id;
    private Role role;
}
