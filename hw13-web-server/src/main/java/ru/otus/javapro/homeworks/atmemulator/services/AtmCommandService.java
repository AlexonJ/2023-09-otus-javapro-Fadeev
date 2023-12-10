package ru.otus.javapro.homeworks.atmemulator.services;

import ru.otus.javapro.homeworks.atmemulator.exceptions.WrongAmountException;
import ru.otus.javapro.homeworks.atmemulator.exceptions.WrongDenominationException;
import ru.otus.javapro.homeworks.atmemulator.exceptions.WrongPinCodeException;

public interface AtmCommandService {
    String getMoney(String pinCode, long amount) throws WrongPinCodeException, WrongAmountException;

    String depositCash(String pinCode, Long denomination, Long amount) throws WrongPinCodeException, WrongDenominationException;

    String getBalance(String pinCode) throws WrongPinCodeException;

    String changePin(String pinCode, String newPinCode) throws WrongPinCodeException;
}
