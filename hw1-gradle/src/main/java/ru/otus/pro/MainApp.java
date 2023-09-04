package ru.otus.pro;

import com.google.common.base.Joiner;

public class MainApp {
    public static void main(String[] args) {
        System.out.printf("Hello! I'm running with args: %s", Joiner.on("; ").join(args));
    }
}