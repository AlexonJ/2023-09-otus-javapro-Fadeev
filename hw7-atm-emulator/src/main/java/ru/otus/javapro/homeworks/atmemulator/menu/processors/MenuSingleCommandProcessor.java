package ru.otus.javapro.homeworks.atmemulator.menu.processors;


import ru.otus.javapro.homeworks.atmemulator.menu.MenuOption;

public interface MenuSingleCommandProcessor {
    void processCommand();
    MenuOption getProcessedCommandOption();
}
