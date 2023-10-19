package ru.otus.javapro.homeworks.atmemulator.services;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtmStopServiceImpl implements AtmStopService {
    private final AtomicBoolean executionFlag;

    public AtmStopServiceImpl() {
        this.executionFlag = new AtomicBoolean(true);
    }

    @Override
    public boolean isAtmRunning() {
        return executionFlag.get();
    }

    @Override
    public void stopAtm() {
        executionFlag.set(false);
    }
}
