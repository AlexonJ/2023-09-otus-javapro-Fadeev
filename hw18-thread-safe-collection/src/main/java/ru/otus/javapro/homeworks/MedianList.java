package ru.otus.javapro.homeworks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public final class MedianList<T extends Number> {

    public static final Logger log = LoggerFactory.getLogger(Main.class.getSimpleName());

    private final List<T> elements = new LinkedList<>();

    public int size() {
        synchronized (this) {
            return elements.size();
        }
    }

    public void add(T item) {
        synchronized (this) {
            log.info("Start adding element {}", item);
            var interationsNumber = insertSorted(item, 0, elements.size() - 1, 0);
            log.info("Element added in {} iterations", interationsNumber);
            log.info("Now collection contains %s".formatted(toString()));
        }
    }

    public int insertSorted(T item, int beginIndex, int endIndex, int interationsNumber) {
        interationsNumber++;
        if (elements.isEmpty()) {
            elements.add(item);
        } else if (beginIndex == endIndex) {
            if (((Comparable<T>)elements.get(beginIndex)).compareTo(item) < 0)
                elements.add(beginIndex + 1, item);
            else elements.add(beginIndex, item);
        } else {
            int middleIndex = beginIndex + (endIndex - beginIndex) / 2;
            if (((Comparable<T>)elements.get(middleIndex)).compareTo(item) < 0) {
                return insertSorted(item, beginIndex, Math.max(middleIndex - 1, beginIndex), interationsNumber);
            } else {
                return insertSorted(item, middleIndex + 1, endIndex, interationsNumber);
            }
        }
        return interationsNumber;
    }

    public void remove(T item) {
        synchronized (this) {
            log.info("Start removing element {}", item);
            elements.remove(item);
            log.info("Now collection contains %s".formatted(toString()));
        }
    }

    public void remove(int index) {
        synchronized (this) {
            log.info("Start removing element with index {}", index);
            T removedElement = elements.remove(index);
            log.info("Element %s with index %d removed. Now collection contains %s".formatted(removedElement, index, toString()));

        }
    }

    public Double getMedian() {
        synchronized (this) {
            log.info("Start calculating median for array {}", elements);

            double median = 0D;

            if (elements.size() == 0) {
                median = Double.NaN;
            } else {

                elements.sort(Comparator.comparingDouble(Number::doubleValue));

                var middle = elements.size() / 2;

                if (elements.size() % 2 == 0) {
                    median = (elements.get(middle - 1).doubleValue() + elements.get(middle).doubleValue()) / 2;
                } else {
                    median = elements.get(middle).doubleValue();
                }
            }

            log.info("Calculated median is {}", median);

            return median;
        }
    }

    public String toString() {
        synchronized (this) {
            return elements.toString();
        }
    }
}
