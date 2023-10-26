package ru.otus.javapro.homeworks.hw10homemadeorm.core.sessionmanager;

public interface TransactionRunner {

    <T> T doInTransaction(TransactionAction<T> action);
}
