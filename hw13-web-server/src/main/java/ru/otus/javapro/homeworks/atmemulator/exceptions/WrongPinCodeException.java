package ru.otus.javapro.homeworks.atmemulator.exceptions;


public class WrongPinCodeException extends Exception{
    public WrongPinCodeException(String message){
        super(message);
    }
}
