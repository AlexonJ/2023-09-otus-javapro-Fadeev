package ru.otus.javapro.homeworks.atmemulator.services;

import ru.otus.javapro.homeworks.atmemulator.menu.MenuOption;
import ru.otus.javapro.homeworks.atmemulator.menu.MenuOptionsRegistry;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class TerminalServiceImpl implements TerminalService{
    private static final int MAX_ATTEMPTS = 10;

    private final PrintStream printStream;

    private final Scanner scanner;

    public TerminalServiceImpl(PrintStream printStream,
                            InputStream inputStream) {

        this.printStream = printStream;
        this.scanner = new Scanner(inputStream);
    }

    @Override
    public void printLine(String s) {
        printStream.println(s);
    }

    @Override
    public void printFormattedLine(String s, Object... args) {
        printStream.printf(s + "%n", args);
    }

    @Override
    public String readStringWithPrompt(String prompt) {
        printLine(prompt);
        return scanner.nextLine();
    }

    @Override
    public int readIntForRange(int min, int max, String errorMessage) {
        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            try {
                var stringValue = scanner.nextLine();
                int intValue = Integer.parseInt(stringValue);
                if (intValue < min || intValue > max) {
                    throw new IllegalArgumentException();
                }
                return intValue;
            } catch (IllegalArgumentException e) {
                printLine(errorMessage);
            }
        }
        throw new IllegalArgumentException("Error during reading int value");
    }

    @Override
    public int readIntForRangeWithPrompt(int min, int max, String prompt, String errorMessage) {
        printLine(prompt);
        return readIntForRange(min, max, errorMessage);
    }

    @Override
    public int getSelectedMenuOption(MenuOptionsRegistry optionsRegistry){
        List<MenuOption> options = optionsRegistry.getAvailableMenuOptions();
        options.forEach(menuOption -> printFormattedLine("%s.%s", menuOption.getId(), menuOption.getDescription()));
        return readIntForRangeWithPrompt(1, options.size(), "Choose option: ", "Incorrect option");
    }
}
