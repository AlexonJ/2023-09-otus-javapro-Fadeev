package ru.otus.javapro.homeworks.hw14dependencyinjection.exception;

public class DuplicateComponentException extends Exception{
    public DuplicateComponentException(String message) {
        super(message);
    }

}
