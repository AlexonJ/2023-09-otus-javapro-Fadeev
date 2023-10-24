package ru.otus.javapro.homeworks.hw8designpatterns.listener.homework;

import ru.otus.javapro.homeworks.hw8designpatterns.listener.Listener;
import ru.otus.javapro.homeworks.hw8designpatterns.model.Message;

import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {

    @Override
    public void onUpdated(Message msg) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        throw new UnsupportedOperationException();
    }
}
