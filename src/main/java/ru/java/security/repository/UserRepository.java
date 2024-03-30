package ru.java.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.java.security.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
