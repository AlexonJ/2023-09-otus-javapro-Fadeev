package ru.otus.javapro.homeworks.atmemulator.services;

import lombok.AllArgsConstructor;
import ru.otus.javapro.homeworks.atmemulator.exceptions.MenuItemIndexOutOfBoundsException;
import ru.otus.javapro.homeworks.atmemulator.menu.MenuOptionsRegistry;
import ru.otus.javapro.homeworks.atmemulator.menu.processors.MenuCommandsProcessor;

@AllArgsConstructor
public class AtmServiceImpl implements AtmService{

    private final TerminalService terminal;
    private final AtmStopService atmStopService;
    private final MenuOptionsRegistry mainMenuOptionsRegistry;
    private final MenuCommandsProcessor menuCommandsProcessor;

    @Override
    public void run() {
        while (atmStopService.isAtmRunning()) {
            int selectedOption = terminal.getSelectedMenuOption(mainMenuOptionsRegistry);
            processMenuCommand(selectedOption);
        }
    }

    private void processMenuCommand(int selectedMenuItemId) {
        var selectedMenuOption = mainMenuOptionsRegistry.getMenuOptionById(selectedMenuItemId)
                .orElseThrow(() -> new MenuItemIndexOutOfBoundsException("Given menu item index is out of range"));

        menuCommandsProcessor.processMenuCommand(selectedMenuOption);
    }
}
