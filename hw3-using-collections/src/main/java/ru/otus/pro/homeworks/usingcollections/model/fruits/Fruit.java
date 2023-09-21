package ru.otus.pro.homeworks.usingcollections.model.fruits;

public abstract class Fruit {

    private int weight;

    public Fruit(){
        this.weight = 100;
    }

    public Fruit(int weight){
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

}