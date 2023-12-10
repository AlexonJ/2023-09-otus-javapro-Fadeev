package ru.otus.javapro.homeworks.atmemulator.services;

public interface AtmSecurityService {

    void setPinCode(String newPinCode);

    boolean isPinCorrect(String pinCode);
}
