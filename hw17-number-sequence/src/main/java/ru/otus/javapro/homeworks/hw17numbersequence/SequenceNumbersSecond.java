package ru.otus.javapro.homeworks.hw17numbersequence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class SequenceNumbersSecond {

    private static final Logger log = LoggerFactory.getLogger(ThreadsMain.class);

    private final Object lock = new Object();
    private Thread firstThread;
    private boolean thread1Turn = true;

    public void printNumbers() {

        int counter = 1;
        for (; counter <= 9; counter++) {
            performIteration(counter);
        }
        for (; counter >= 1; counter--) {
            performIteration(counter);
        }
    }

    private void performIteration(int counter) {
        synchronized (lock) {
            try {
                while ((thread1Turn && !Thread.currentThread().equals(firstThread)) ||
                        (!thread1Turn && Thread.currentThread().equals(firstThread)) ||
                        Objects.isNull(firstThread)
                ) {
                    lock.wait();
                }

                log.info(Thread.currentThread().getName() + ": " + counter);

                thread1Turn = !thread1Turn;
                lock.notifyAll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void setFirstThread(Thread firstThread) {
        this.firstThread = firstThread;
    }

}

