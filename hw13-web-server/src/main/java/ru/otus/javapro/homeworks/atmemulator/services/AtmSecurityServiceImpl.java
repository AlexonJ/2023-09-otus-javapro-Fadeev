package ru.otus.javapro.homeworks.atmemulator.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AtmSecurityServiceImpl implements AtmSecurityService {

    public String pinCode;
    public AtmSecurityServiceImpl(@Value("${service.atm-pin-code-default}") String pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public void setPinCode(String newPinCode){
        this.pinCode = newPinCode;
    }

    @Override
    public boolean isPinCorrect(String pinCode){
        return this.pinCode.equals(pinCode);
    }
}
