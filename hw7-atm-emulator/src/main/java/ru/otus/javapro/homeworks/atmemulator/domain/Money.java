package ru.otus.javapro.homeworks.atmemulator.domain;

import java.util.List;

public interface Money {

    List<Long> getBanknoteDenominationsList();

    String getCurrency();
}
