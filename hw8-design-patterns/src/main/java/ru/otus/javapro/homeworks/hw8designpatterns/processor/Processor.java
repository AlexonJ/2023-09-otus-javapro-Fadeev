package ru.otus.javapro.homeworks.hw8designpatterns.processor;

import ru.otus.javapro.homeworks.hw8designpatterns.exceptions.ProcessingException;
import ru.otus.javapro.homeworks.hw8designpatterns.model.Message;

public interface Processor {

    Message process(Message message) throws ProcessingException;
}
