package ru.java.security.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class UserDto {
    private String username;
    private String password;
    private String email;
}

