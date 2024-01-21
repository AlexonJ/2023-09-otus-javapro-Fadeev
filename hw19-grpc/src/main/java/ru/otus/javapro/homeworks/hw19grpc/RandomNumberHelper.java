package ru.otus.javapro.homeworks.hw19grpc;

import java.util.Random;

public class RandomNumberHelper {

    private static final Random random = new Random();

    public static int getRandomId(){
        return random.nextInt(0, 1000);
    }

    public static int getRandomNumber(int bound){
        return random.nextInt(0, bound);
    }
}
