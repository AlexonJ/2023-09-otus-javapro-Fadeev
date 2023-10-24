package ru.otus.javapro.homeworks.hw8designpatterns.processor;

import ru.otus.javapro.homeworks.hw8designpatterns.exceptions.ProcessingException;
import ru.otus.javapro.homeworks.hw8designpatterns.listener.homework.DateTimeProvider;
import ru.otus.javapro.homeworks.hw8designpatterns.model.Message;

import java.time.LocalDateTime;

public class ProcessorThrowingException implements Processor{

    DateTimeProvider dateTimeProvider;

    public ProcessorThrowingException(DateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }

    @Override
    public Message process(Message message) throws ProcessingException {
        if ((dateTimeProvider.getDate().getSecond() % 2) == 0){
            throw new ProcessingException("It is exception that appears every even second");
        }
        return null;
    }
}
