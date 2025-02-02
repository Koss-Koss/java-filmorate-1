package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.TreeSet;

@Data
@Builder
public class User {
    private int id;
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String login;
    @NonNull
    private LocalDate birthday;
    @NonNull
    private String friendlyStatus;
    private TreeSet<Integer> friends;
}
