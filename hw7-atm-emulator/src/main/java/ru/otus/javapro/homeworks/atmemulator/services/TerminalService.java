package ru.otus.javapro.homeworks.atmemulator.services;

import ru.otus.javapro.homeworks.atmemulator.menu.MenuOptionsRegistry;

public interface TerminalService {

    void printLine(String s);
    void printFormattedLine(String s, Object... args);
    String readStringWithPrompt(String prompt);
    int readIntForRange(int min, int max, String errorMessage);
    int readIntForRangeWithPrompt(int min, int max, String prompt, String errorMessage);
    int getSelectedMenuOption(MenuOptionsRegistry optionsRegistry);

}
