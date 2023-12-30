package ru.otus.javapro.homeworks.hw17numbersequence;

import java.util.concurrent.ThreadFactory;

public class NamedThreadFactory implements ThreadFactory {
    private int threadCount = 1;

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, "Thread-" + threadCount++);
    }
}

