package ru.otus.javapro.homeworks.atmemulator.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BanknoteStack{

    private final Map<Long, Long> banknotes = new HashMap<>();

    public Map<Long, Long> getBanknotes() {
        return banknotes;
    }

    public Long getTotalSum() {
        return banknotes.entrySet().stream().mapToLong(value -> value.getKey() * value.getValue()).sum();
    }

    public void addBanknoteStack(BanknoteStack banknoteStack) {
        banknoteStack.getBanknotes().forEach((key, value) -> banknotes.put(key, banknotes.getOrDefault(key, 0L) + value));
    }

    public void takeBanknoteStack(BanknoteStack banknoteStack){
        banknoteStack.getBanknotes().forEach((key, value) -> banknotes.put(key, banknotes.getOrDefault(key, 0L) - value));
    }

    public BanknoteStack getBanknoteStackBySum(Long sum) {
        BanknoteStack banknoteStack = new BanknoteStack();
        getSortedBanknotesStream().forEach(tLongEntry ->
        {
            Long key = tLongEntry.getKey();
            Long value = tLongEntry.getValue();
            banknoteStack.getBanknotes().put(key,
                    Long.min((sum - banknoteStack.getTotalSum())/key, value));
        });
        return banknoteStack;
    }

    public void putBanknote(Long value, Long amount){
        banknotes.put(value, amount);
    }

    public Stream<Map.Entry<Long, Long>> getSortedBanknotesStream(){
        return banknotes.entrySet().stream().sorted((o1, o2) -> o2.getKey().intValue() - o1.getKey().intValue());
    }

    @Override
    public String toString() {
        return getSortedBanknotesStream().filter(tLongEntry -> tLongEntry.getValue() != 0)
                .map(tLongEntry -> tLongEntry.getKey() + "x" + tLongEntry.getValue()).collect(Collectors.joining(", "));
    }

}
