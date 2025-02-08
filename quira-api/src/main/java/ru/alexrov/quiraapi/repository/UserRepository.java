package ru.alexrov.quiraapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alexrov.quiraapi.entity.UserApp;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserApp, Long> {
    Optional<UserApp> findByUsername(String username);

    Optional<UserApp> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
