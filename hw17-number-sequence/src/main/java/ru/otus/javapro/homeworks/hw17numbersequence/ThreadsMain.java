package ru.otus.javapro.homeworks.hw17numbersequence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadsMain {

    public static void main(String[] args) throws InterruptedException {

        SequenceNumbers sequenceThreads = new SequenceNumbers();
        ThreadFactory namedThreadFactory = new NamedThreadFactory();
        ExecutorService pool = Executors.newFixedThreadPool(2, namedThreadFactory);

        pool.submit(sequenceThreads::runFirst);
        pool.submit(sequenceThreads::runSecond);

        pool.shutdown();

        Thread.sleep(100);
        System.out.printf("%nSecond solution:%n");
        SequenceNumbersSecond sequenceThreadsSecond = new SequenceNumbersSecond();

        var thread1 = new Thread(sequenceThreadsSecond::printNumbers, "Thread-1");
        var thread2 = new Thread(sequenceThreadsSecond::printNumbers, "Thread-2");

        thread2.start();
        Thread.sleep(100);
        thread1.start();

        sequenceThreadsSecond.setFirstThread(thread1);

    }

}