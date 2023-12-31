package ru.otus.javapro.homeworks.hw17numbersequence;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CountDownLatch;

public class SequenceNumbers {

    private final Lock lock = new ReentrantLock();

    private final Condition firstCanRun = lock.newCondition();
    private final Condition secondCanRun = lock.newCondition();
    private final static int maxCounterValue = 10;

    private final CountDownLatch firstThreadStartedSignal = new CountDownLatch(1);

    public void runFirst() {
        int counter = 1;
        int step = 1;
        firstThreadStartedSignal.countDown();
        while (counter > 0) {
            if (counter == maxCounterValue) {
                step = -step;
            }
            processInteration(secondCanRun, firstCanRun, counter);
            counter += step;
        }
        lock.lock();
        secondCanRun.signal();
        lock.unlock();
    }

    public void runSecond() {
        try {
            firstThreadStartedSignal.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int counter = 1;
        int step = 1;
        while (counter > 0) {
            if (counter == maxCounterValue) {
                step = -step;
            }
            processInteration(firstCanRun, secondCanRun, counter);
            counter += step;
        }
    }

    public void processInteration(Condition signalCondition, Condition awaitCondition, int conter) {
        lock.lock();
        try {
            System.out.printf("%s %d%n", Thread.currentThread().getName(), conter);
            signalCondition.signal();
            awaitCondition.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
