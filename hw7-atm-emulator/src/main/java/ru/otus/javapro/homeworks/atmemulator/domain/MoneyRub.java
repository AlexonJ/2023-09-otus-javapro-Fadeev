package ru.otus.javapro.homeworks.atmemulator.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MoneyRub implements Money{

    private final List<Long> denominations = new ArrayList<>();

    private final String currency = "RUB";

    public MoneyRub() {
        denominations.add(5000L);
        denominations.add(2000L);
        denominations.add(1000L);
        denominations.add(500L);
        denominations.add(100L);
    }

    @Override
    public List<Long> getBanknoteDenominationsList() {
        return denominations;
    }

    @Override
    public String getCurrency() {
        return currency;
    }

}
