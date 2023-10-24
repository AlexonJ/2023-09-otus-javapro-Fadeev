package ru.otus.javapro.homeworks.hw8designpatterns.handler;

import ru.otus.javapro.homeworks.hw8designpatterns.model.Message;
import ru.otus.javapro.homeworks.hw8designpatterns.listener.Listener;

public interface Handler {
    Message handle(Message msg);

    void addListener(Listener listener);
    void removeListener(Listener listener);
}
