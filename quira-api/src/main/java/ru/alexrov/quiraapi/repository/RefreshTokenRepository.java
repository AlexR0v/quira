package ru.alexrov.quiraapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexrov.quiraapi.entity.RefreshToken;
import ru.alexrov.quiraapi.entity.UserApp;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    void deleteAllByUser(UserApp user);
}
