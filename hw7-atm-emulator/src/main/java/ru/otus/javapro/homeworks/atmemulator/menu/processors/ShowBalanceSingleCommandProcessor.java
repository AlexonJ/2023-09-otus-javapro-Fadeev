package ru.otus.javapro.homeworks.atmemulator.menu.processors;

import lombok.AllArgsConstructor;
import ru.otus.javapro.homeworks.atmemulator.menu.MenuOption;
import ru.otus.javapro.homeworks.atmemulator.services.CashStorageService;
import ru.otus.javapro.homeworks.atmemulator.services.TerminalService;

@AllArgsConstructor
public class ShowBalanceSingleCommandProcessor implements MenuSingleCommandProcessor {

    private final MenuOption processedCommandOption;
    private final TerminalService terminalService;
    private final CashStorageService cashStorageService;

    @Override
    public void processCommand() {
        terminalService.printFormattedLine("Total balance is: %s", cashStorageService.getTotalBalance());
        terminalService.printLine("");
    }

    @Override
    public MenuOption getProcessedCommandOption() {
        return processedCommandOption;
    }
}