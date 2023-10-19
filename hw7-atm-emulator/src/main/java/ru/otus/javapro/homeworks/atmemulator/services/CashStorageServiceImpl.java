package ru.otus.javapro.homeworks.atmemulator.services;


import lombok.RequiredArgsConstructor;
import ru.otus.javapro.homeworks.atmemulator.domain.BanknoteStack;
import ru.otus.javapro.homeworks.atmemulator.domain.Money;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class CashStorageServiceImpl implements CashStorageService {

    private final BanknoteStack banknotes;
    private final Money money;

    @Override
    public Long getTotalBalance() {
        return banknotes.getTotalSum();
    }

    @Override
    public void putBanknoteStack(BanknoteStack banknoteStack){
        banknotes.addBanknoteStack(banknoteStack);
    }

    @Override
    public void takeBanknoteStack(BanknoteStack banknoteStack){
        banknotes.takeBanknoteStack(banknoteStack);
    }

    @Override
    public Boolean isAmountCanBeIssued(Long amount){
        return Objects.equals(amount, getBanknoteStackBySum(amount).getTotalSum());
    }

    @Override
    public BanknoteStack getBanknoteStackBySum(Long amount){
        return banknotes.getBanknoteStackBySum(amount);
    }

    @Override
    public List<Long> getBanknoteDenominations(){
        return money.getDenominations();
    }
}
