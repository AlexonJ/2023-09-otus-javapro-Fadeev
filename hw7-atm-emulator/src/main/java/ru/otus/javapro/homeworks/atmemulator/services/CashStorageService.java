package ru.otus.javapro.homeworks.atmemulator.services;

import ru.otus.javapro.homeworks.atmemulator.domain.BanknoteStack;

import java.util.List;

public interface CashStorageService {

    Long getTotalBalance();

    void putBanknoteStack(BanknoteStack banknoteStack);

    void takeBanknoteStack(BanknoteStack banknoteStack);

    Boolean isAmountCanBeIssued(Long amount);

    BanknoteStack getBanknoteStackBySum(Long amount);

    List<Long> getBanknoteDenominations();
}
