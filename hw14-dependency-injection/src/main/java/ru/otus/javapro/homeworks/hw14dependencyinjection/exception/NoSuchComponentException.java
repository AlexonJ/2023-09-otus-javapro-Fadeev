package ru.otus.javapro.homeworks.hw14dependencyinjection.exception;

public class NoSuchComponentException extends Exception{
    public NoSuchComponentException(String message) {
        super(message);
    }
}
