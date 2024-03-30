package ru.java.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.java.security.dto.UserDto;
import ru.java.security.entity.User;
import ru.java.security.enums.Role;
import ru.java.security.mapper.UserMapper;
import ru.java.security.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto createUser(final UserDto userDto) {
        final User user = userMapper.toUser(userDto);
        final Optional<User> existsUser = userRepository.findByEmail(userDto.getEmail());
        if (existsUser.isPresent()) {
            user.setId(existsUser.get().getId());
            return userMapper.toUserDto(userRepository.save(user));
        }
        final User newUser = userRepository.save(user);
        return userMapper.toUserDto(newUser);
    }

    public User getByUsername(final String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public User getCurrentUser() {
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    @Deprecated
    public void getAdmin() {
        final User user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        createUser(userMapper.toUserDto(user));
    }
}
