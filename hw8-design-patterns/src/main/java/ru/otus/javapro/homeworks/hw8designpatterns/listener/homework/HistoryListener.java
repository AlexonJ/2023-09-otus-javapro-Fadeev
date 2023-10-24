package ru.otus.javapro.homeworks.hw8designpatterns.listener.homework;

import ru.otus.javapro.homeworks.hw8designpatterns.listener.Listener;
import ru.otus.javapro.homeworks.hw8designpatterns.listener.homework.HistoryReader;
import ru.otus.javapro.homeworks.hw8designpatterns.model.Message;

import java.time.LocalDateTime;
import java.util.*;

public class HistoryListener implements Listener, HistoryReader {

    List<StateSnapshot<Message>> historyList = new ArrayList<>();

    @Override
    public void onUpdated(Message msg) {
        historyList.add(new StateSnapshot<>(new Message(msg), LocalDateTime.now()));
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return historyList.stream().filter(
                snapshot -> snapshot.getObject().getId() == id)
                .min(Comparator.comparing(StateSnapshot::getDateTime))
                .map(StateSnapshot::getObject);
    }
}
