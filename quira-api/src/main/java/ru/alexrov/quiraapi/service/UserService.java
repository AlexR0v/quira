package ru.alexrov.quiraapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.alexrov.quiraapi.entity.UserApp;
import ru.alexrov.quiraapi.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public void save(UserApp user) {
        repository.save(user);
    }


    public void create(UserApp user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }
        this.save(user);
    }

    public UserApp getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    public UserApp getByEmail(String email) {
        Optional<UserApp> userByEmail = repository.findByEmail(email);
        Optional<UserApp> userByName = repository.findByUsername(email);
        if (userByEmail.isEmpty() && userByName.isPresent()) {
            return repository.findByUsername(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден " + email));
        }
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден " + email));

    }

    public UserDetailsService userDetailsService() {
        return this::getByEmail;
    }

    public UserApp getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }
}
