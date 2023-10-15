package ru.otus.javapro.homeworks.atmemulator.domain;

import java.util.Map;
import java.util.stream.Stream;

public interface BanknoteStack {

    BanknoteStack getBanknoteStackBySum(Long sum);

    Stream<Map.Entry<Long, Long>> getSortedBanknotesStream();

    Long getTotalSum();
    Map<Long, Long> getBanknotes();

    void addBanknoteStack(BanknoteStack banknoteStack);

    void takeBanknoteStack(BanknoteStack banknoteStack);

    void putBanknote(Long value, Long amount);
}
