package ru.otus.javapro.homeworks.atmemulator.services;

import lombok.AllArgsConstructor;
import ru.otus.javapro.homeworks.atmemulator.exceptions.MenuItemIndexOutOfBoundsException;
import ru.otus.javapro.homeworks.atmemulator.menu.MenuOptionsRegistry;
import ru.otus.javapro.homeworks.atmemulator.menu.processors.MenuCommandsProcessor;

@AllArgsConstructor
public class AtmServiceImpl implements AtmService{

    private final TerminalService terminalService;
    private final AtmStopService atmStopService;
    private final MenuOptionsRegistry mainMenuOptionsRegistry;
    private final MenuCommandsProcessor menuCommandsProcessor;

    @Override
    public void run() {
        while (atmStopService.isAtmRunning()) {
            terminalService.printLine("");
            terminalService.printLine("Welcome to main menu.");
            int selectedOption = terminalService.getSelectedMenuOption(mainMenuOptionsRegistry);
            processMenuCommand(selectedOption);
        }
    }

    private void processMenuCommand(int selectedMenuItemId) {
        var selectedMenuOption = mainMenuOptionsRegistry.getMenuOptionById(selectedMenuItemId)
                .orElseThrow(() -> new MenuItemIndexOutOfBoundsException("Given menu item index is out of range"));

        menuCommandsProcessor.processMenuCommand(selectedMenuOption);
    }
}
