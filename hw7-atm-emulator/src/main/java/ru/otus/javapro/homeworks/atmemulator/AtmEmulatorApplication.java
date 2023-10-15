package ru.otus.javapro.homeworks.atmemulator;

import ru.otus.javapro.homeworks.atmemulator.services.AtmBuilder;

public class AtmEmulatorApplication {
    public static void main(String[] args) {
        AtmBuilder.build().run();
    }
}