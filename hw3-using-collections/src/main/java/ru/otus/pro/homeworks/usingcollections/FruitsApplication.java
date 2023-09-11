package ru.otus.pro.homeworks.usingcollections;
import ru.otus.pro.homeworks.usingcollections.model.fruits.Apple;
import ru.otus.pro.homeworks.usingcollections.model.fruits.Fruit;
import ru.otus.pro.homeworks.usingcollections.model.Box;
import ru.otus.pro.homeworks.usingcollections.model.fruits.Orange;
import ru.otus.pro.homeworks.usingcollections.model.fruits.Watermelon;

public class FruitsApplication extends Fruit {
    public static void main(String[] args) {

        Box<Apple> appleBox = new Box<>();

        appleBox.put(new Apple(200));
        appleBox.put(new Apple(300));
        appleBox.put(new Apple(250));
        appleBox.put(new Apple());

        Box<Orange> orangeBox = new Box<>();

        orangeBox.put(new Orange(300));
        orangeBox.put(new Orange(400));
        orangeBox.put(new Orange(350));
        orangeBox.put(new Orange());
//        orangeBox.put(new Apple()); - we can't do this
        Box<Orange> orangeBox2 = new Box<>();

        orangeBox2.put(new Orange(350));
        orangeBox2.put(new Orange(300));
        orangeBox2.put(new Orange(300));
        orangeBox2.put(new Orange());

        Box<Fruit> mixedBox = new Box<>();

        mixedBox.put(new Orange(350));
        mixedBox.put(new Apple(300));
        mixedBox.put(new Watermelon(6000));
        mixedBox.put(new Orange());

        System.out.printf("Box with apples weights %s g.%n", appleBox.getWeight());
        System.out.printf("Box with oranges weights %s g.%n", orangeBox.getWeight());
        System.out.printf("Second box with oranges weights %s g.%n", orangeBox2.getWeight());
        System.out.printf("Mixed box weights %s g.%n", mixedBox.getWeight());

        orangeBox.transfer(orangeBox2);
        System.out.println("Fruits from first box with oranges were transferred to the second box.");
        System.out.printf("Second box with oranges now weights %s g.%n", orangeBox2.getWeight());

        appleBox.clear();
        appleBox.put(new Apple(200));
        appleBox.put(new Apple(300));

        orangeBox.clear();
        orangeBox.put(new Orange(500));

        System.out.printf("Box with apples now weights %s g.%n", appleBox.getWeight());
        System.out.printf("Box with oranges now weights %s g.%n", orangeBox.getWeight());
        System.out.printf("Weight of the apple box and orange box are the same: %s%n", appleBox.compare(orangeBox));
        System.out.printf("Weight of the first orange box and second orange box is the same: %s%n", appleBox.compare(orangeBox));
    }
}
