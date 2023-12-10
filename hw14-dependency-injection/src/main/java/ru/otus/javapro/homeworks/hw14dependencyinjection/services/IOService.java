package ru.otus.javapro.homeworks.hw14dependencyinjection.services;

public interface IOService {
    void out(String message);
    String readLn(String prompt);
    int readInt(String prompt);
}
