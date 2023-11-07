package ru.otus.javapro.homeworks.hw8designpatterns.listener;

import ru.otus.javapro.homeworks.hw8designpatterns.model.Message;

public interface Listener {
    void onUpdated(Message msg);
}
