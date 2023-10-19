package ru.otus.javapro.homeworks.atmemulator.menu.processors;

import ru.otus.javapro.homeworks.atmemulator.menu.MenuOption;
import ru.otus.javapro.homeworks.atmemulator.services.AtmStopService;
import ru.otus.javapro.homeworks.atmemulator.services.TerminalService;

public class StopAtmSingleCommandProcessor implements MenuSingleCommandProcessor {
    private final MenuOption processedCommandOption;
    private final AtmStopService applicationStopService;
    private final TerminalService terminalService;

    public StopAtmSingleCommandProcessor(MenuOption processedCommandOption,
                                         AtmStopService atmStopService, TerminalService terminalService) {
        this.applicationStopService = atmStopService;
        this.processedCommandOption = processedCommandOption;
        this.terminalService = terminalService;
    }

    @Override
    public void processCommand() {
        var exitConfirmation = terminalService.readStringWithPrompt("Confirm exit? (y/n)");
        if (exitConfirmation.equalsIgnoreCase("y")) {
            applicationStopService.stopAtm();
        }
    }

    @Override
    public MenuOption getProcessedCommandOption() {
        return processedCommandOption;
    }
}
