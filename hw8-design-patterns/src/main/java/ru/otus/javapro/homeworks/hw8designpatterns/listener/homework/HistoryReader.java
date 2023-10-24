package ru.otus.javapro.homeworks.hw8designpatterns.listener.homework;

import ru.otus.javapro.homeworks.hw8designpatterns.model.Message;

import java.util.Optional;

public interface HistoryReader {

    Optional<Message> findMessageById(long id);
}
