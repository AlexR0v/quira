package ru.alexrov.quiraapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alexrov.quiraapi.entity.RefreshToken;
import ru.alexrov.quiraapi.entity.UserApp;
import ru.alexrov.quiraapi.repository.RefreshTokenRepository;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateRefreshToken(UserApp user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(java.util.UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(1000 * 60 * 60 * 24 * 7)); // 1 неделя
        return refreshTokenRepository.save(refreshToken);
    }

    public boolean verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            return false;
        }
        return true;
    }

    public void deleteByUser(UserApp user) {
        refreshTokenRepository.deleteAllByUser(user);
    }
}
