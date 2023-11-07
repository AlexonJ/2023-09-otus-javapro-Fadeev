package ru.otus.javapro.homeworks.hw8designpatterns.processor;

import ru.otus.javapro.homeworks.hw8designpatterns.model.Message;

public class ProcessorReplaceFieldsValues implements Processor{

    @Override
    public Message process(Message message) {
        String field11 = message.getField11();
        message.setField11(message.getField12());
        message.setField12(field11);
        return message;
    }

}
