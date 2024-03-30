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
    User toUser(UserDto userDto);

    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "email", source = "email")
    UserDto toUserDto(User user);
}
