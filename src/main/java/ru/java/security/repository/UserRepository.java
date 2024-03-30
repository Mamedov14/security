package ru.java.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.java.security.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
