package ru.otus.javapro.homeworks.hw8designpatterns.listener.homework;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class StateSnapshot<T> {

    T object;
    LocalDateTime dateTime;
}
