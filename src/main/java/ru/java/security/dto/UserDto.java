package ru.java.security.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.java.security.enums.Role;

@Getter
@Setter
@Builder
public class UserDto {
    private String username;
    private String password;
    private String email;
    private Role role;
}

