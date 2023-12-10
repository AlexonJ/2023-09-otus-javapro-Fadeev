package ru.otus.javapro.homeworks.atmemulator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.javapro.homeworks.atmemulator.exceptions.WrongAmountException;
import ru.otus.javapro.homeworks.atmemulator.exceptions.WrongDenominationException;
import ru.otus.javapro.homeworks.atmemulator.exceptions.WrongPinCodeException;
import ru.otus.javapro.homeworks.atmemulator.services.AtmCommandServiceImpl;

@RestController
public class AtmController {

    private final AtmCommandServiceImpl commandService;

    public AtmController(@Autowired AtmCommandServiceImpl commandService) {
        this.commandService = commandService;
    }

    @GetMapping(path = "getCash")
    public ResponseEntity<String> getCash(
            @RequestParam String pinCode,
            @RequestParam Long amount) {

        String returnMessage;
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            returnMessage = commandService.getMoney(pinCode, amount);
        } catch (WrongPinCodeException e) {
            returnMessage = e.getMessage();
            httpStatus = HttpStatus.FORBIDDEN;
        } catch (WrongAmountException e) {
            returnMessage = e.getMessage();
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(returnMessage, httpStatus);

    }

    @GetMapping(path = "depositCash")
    public ResponseEntity<String> depositCash(
            @RequestParam String pinCode,
            @RequestParam Long denomination,
            @RequestParam Long amount) {

        String returnMessage;
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            returnMessage = commandService.depositCash(pinCode, denomination, amount);
        } catch (WrongPinCodeException e) {
            returnMessage = e.getMessage();
            httpStatus = HttpStatus.FORBIDDEN;
        } catch (WrongDenominationException e) {
            returnMessage = e.getMessage();
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(returnMessage, httpStatus);
    }

    @GetMapping(path = "getBalance")
    public ResponseEntity<String> getBalance(
            @RequestParam String pinCode) {

        String returnMessage;
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            returnMessage = commandService.getBalance(pinCode);
        } catch (WrongPinCodeException e) {
            returnMessage = e.getMessage();
            httpStatus = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<>(returnMessage, httpStatus);

    }

    @GetMapping(path = "changePin")
    public ResponseEntity<String> changePin(
            @RequestParam String pinCode,
            @RequestParam String newPinCode) {

        String returnMessage;
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            returnMessage = commandService.changePin(pinCode, newPinCode);
        } catch (WrongPinCodeException e) {
            returnMessage = e.getMessage();
            httpStatus = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<>(returnMessage, httpStatus);

    }
}
