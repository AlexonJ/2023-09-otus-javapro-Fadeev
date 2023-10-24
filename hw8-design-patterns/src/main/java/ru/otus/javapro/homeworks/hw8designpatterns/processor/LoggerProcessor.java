package ru.otus.javapro.homeworks.hw8designpatterns.processor;

import ru.otus.javapro.homeworks.hw8designpatterns.exceptions.ProcessingException;
import ru.otus.javapro.homeworks.hw8designpatterns.model.Message;

public class LoggerProcessor implements Processor {


    private final Processor processor;

    public LoggerProcessor(Processor processor) {
        this.processor = processor;
    }

    @Override
    public Message process(Message message) throws ProcessingException {
        System.out.println("log processing message:" + message);
        return processor.process(message);
    }
}
