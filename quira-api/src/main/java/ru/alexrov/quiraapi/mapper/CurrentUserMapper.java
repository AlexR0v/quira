package ru.alexrov.quiraapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.alexrov.quiraapi.dto.CurrentUserResponse;
import ru.alexrov.quiraapi.entity.UserApp;

@Mapper(componentModel = "spring")
public interface CurrentUserMapper {

    @Mapping(target = "id", source = "user.id")
    CurrentUserResponse toCurrentUserResponse(UserApp user);
}
