package ru.java.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.java.security.dto.UserDto;
import ru.java.security.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "role", source = "role")
    User toUser(final UserDto userDto);

    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "role", source = "role")
    UserDto toUserDto(final User user);
}
