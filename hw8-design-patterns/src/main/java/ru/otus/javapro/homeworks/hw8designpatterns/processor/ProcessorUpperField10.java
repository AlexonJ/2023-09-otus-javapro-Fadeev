package ru.otus.javapro.homeworks.hw8designpatterns.processor;

import ru.otus.javapro.homeworks.hw8designpatterns.model.Message;

public class ProcessorUpperField10 implements Processor {

    @Override
    public Message process(Message message) {
        return message.toBuilder().field4(message.getField10().toUpperCase()).build();
    }
}
