package ru.otus.javapro.homeworks.hw10homemadeorm.core.sessionmanager;

import java.sql.Connection;
import java.util.function.Function;

public interface TransactionAction<T> extends Function<Connection, T> {
}
