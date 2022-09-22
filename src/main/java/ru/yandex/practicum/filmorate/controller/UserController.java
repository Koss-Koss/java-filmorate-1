package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exeptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    private final Map<Integer, User> users = new HashMap<>();

    /**
     * получение списка пользователей
     */
    @GetMapping
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    /**
     * создание пользователя
     */
    @PostMapping("/user")
    public User addUser(@RequestBody User user) throws ValidationException {
        isValidUsers(user);
        log.debug("Сохранили: {}", user);
        users.put(user.getId(), user);
        return user;
    }

    /**
     * обновление пользователя
     */
    @PutMapping("/user")
    public User updateUser(@RequestBody User user) throws ValidationException {
        isValidUsers(user);
        log.debug("Обновили: {}", user);
        users.put(user.getId(), user);
        return user;
    }

    /**
     * Валидация фильмов
     */
    protected void isValidUsers(@RequestBody User user) throws ValidationException {
        if (user.getEmail().isBlank()) {
            log.warn("Ошибка в email: {}", user);
            throw new ValidationException("Пользователь не соответствует условиям: " +
                    "- email не должен быть пустым");
        }
        if (!user.getEmail().contains("@")) {
            log.warn("Ошибка в email: {}", user);
            throw new ValidationException("Пользователь не соответствует условиям: " +
                    "- email должен содержать - \"@\"");
        }
        if (user.getLogin().isBlank()) {
            log.warn("Ошибка в логине: {}", user);
            throw new ValidationException("Пользователь не соответствует условиям: " +
                    "- логин не должен быть пустым");
        }
        if (user.getName().isBlank()) {
            log.debug("Имя пусто - заменено логином: {}", user);
            user.setName(user.getLogin());
        }
        if (user.getBirthday().isAfter(LocalDate.now())) {
            log.warn("Ошибка в дате рождения: {}", user);
            throw new ValidationException("Пользователь не соответствует условиям:\n" +
                    "- дата рождения не может быть в будущем");
        }
    }
}