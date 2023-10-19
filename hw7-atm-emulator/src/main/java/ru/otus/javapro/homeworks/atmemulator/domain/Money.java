package ru.otus.javapro.homeworks.atmemulator.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Money{

    private final List<Long> denominations = new ArrayList<>();
    private final String currency;

    public Money(String currency, Long ... denomination) {
        this.currency = currency;
        this.denominations.addAll(List.of(denomination));
    }

}