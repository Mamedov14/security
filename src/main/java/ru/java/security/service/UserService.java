package ru.java.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.java.security.dto.UserDto;
import ru.java.security.entity.User;
import ru.java.security.mapper.UserMapper;
import ru.java.security.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto createUser(final UserDto userDto) {
        final User user = userMapper.toUser(userDto);
        final User savedUser = userRepository.save(user);
        return userMapper.toUserDto(savedUser);
    }
}
