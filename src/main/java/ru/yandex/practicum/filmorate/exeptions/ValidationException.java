package ru.yandex.practicum.filmorate.exeptions;

public class ValidationException extends Exception {
    public ValidationException(final String message) {
        super(message);
    }
}