package ru.otus.pro.homeworks.usingcollections.model;

import ru.otus.pro.homeworks.usingcollections.model.fruits.Fruit;

import java.util.ArrayList;
import java.util.List;

public class Box <T extends Fruit> implements Comparable <Box<T>>{

    private final List<T> container;

    public Box() {
        this.container = new ArrayList<>();
    }

    public void put(T fruit){
        container.add(fruit);
    }

    public void putAll(List<T> fruits){
        container.addAll(fruits);
    }

    public List<T> getFruits(){
        return container;
    }

    public void clear() {
        container.clear();
    }

    public int getWeight(){
        return container.stream().mapToInt(Fruit::getWeight).sum();
    }

    public void transfer(Box<T> newBox) {
        newBox.putAll(this.getFruits());
        this.clear();
    }

    @Override
    public int compareTo(Box o) {
        return this.getWeight() - o.getWeight();
    }

    public boolean compare(Box o) {
        return this.compareTo(o) == 0;
    }

}
