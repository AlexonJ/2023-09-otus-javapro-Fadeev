package ru.otus.javapro.homeworks.hw8designpatterns.listener;

import ru.otus.javapro.homeworks.hw8designpatterns.model.Message;

public class ListenerPrinterConsole implements Listener {

    @Override
    public void onUpdated(Message msg) {
        var logString = String.format("oldMsg:%s", msg);
        System.out.println(logString);
    }
}
