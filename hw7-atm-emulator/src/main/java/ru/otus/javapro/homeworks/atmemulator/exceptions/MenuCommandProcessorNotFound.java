package ru.otus.javapro.homeworks.atmemulator.exceptions;

public class MenuCommandProcessorNotFound extends RuntimeException {
    public MenuCommandProcessorNotFound(String message) {
        super(message);
    }
}
