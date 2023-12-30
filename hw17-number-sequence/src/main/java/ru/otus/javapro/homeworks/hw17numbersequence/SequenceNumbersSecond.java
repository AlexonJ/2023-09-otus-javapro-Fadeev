package ru.otus.javapro.homeworks.hw17numbersequence;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

public class SequenceNumbersSecond {

    private final Object lock = new Object();
    private Thread firstThread;
    private boolean thread1Turn = true;

    public void printNumbers() {
        synchronized (lock) {
            int counter = 1;
            for (; counter <= 9; counter++) {
                PerformIteration(counter);
            }
            for (; counter >= 1; counter--) {
                PerformIteration(counter);
            }
        }
    }

    private void PerformIteration(int counter) {
        try {
            while ((thread1Turn && !Thread.currentThread().equals(firstThread)) ||
                    (!thread1Turn && Thread.currentThread().equals(firstThread)) ||
                    Objects.isNull(firstThread)
            ) {
                lock.wait();
            }

            System.out.println(Thread.currentThread().getName() + ": " + counter);

            thread1Turn = !thread1Turn;
            lock.notifyAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public void setFirstThread(Thread firstThread) {
        this.firstThread = firstThread;
    }

}

