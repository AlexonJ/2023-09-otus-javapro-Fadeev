package ru.otus.javapro.homeworks.atmemulator.menu.processors;

import lombok.AllArgsConstructor;
import ru.otus.javapro.homeworks.atmemulator.domain.BanknoteStack;
import ru.otus.javapro.homeworks.atmemulator.menu.MenuOption;
import ru.otus.javapro.homeworks.atmemulator.menu.MenuOptionsRegistry;
import ru.otus.javapro.homeworks.atmemulator.services.CashStorageService;
import ru.otus.javapro.homeworks.atmemulator.services.TerminalService;

@AllArgsConstructor
public class CashDepositSingleCommandProcessor implements MenuSingleCommandProcessor {

    private final MenuOption processedCommandOption;
    private final TerminalService terminalService;
    private final CashStorageService cashStorageService;
    private final MenuOptionsRegistry menuOptionsRegistry;

    @Override
    public void processCommand() {
        int selectedOption;
        do {
            BanknoteStack banknoteStackToAdd = new BanknoteStack();
            terminalService.printLine("Choose banknote to deposit: ");
            selectedOption = terminalService.getSelectedMenuOption(menuOptionsRegistry);
            if (selectedOption - 1 < cashStorageService.getBanknoteDenominations().size()) {
                banknoteStackToAdd.putBanknote(
                        cashStorageService.getBanknoteDenominations().get(selectedOption - 1), getEnteredBanknoteAmount());
                cashStorageService.putBanknoteStack(banknoteStackToAdd);
                terminalService.printFormattedLine("You put: %s (%s).", banknoteStackToAdd.getTotalSum(), banknoteStackToAdd);
                terminalService.waitUntilEnterIsPressed();
            }
        } while (selectedOption - 1 < cashStorageService.getBanknoteDenominations().size());
    }

    private long getEnteredBanknoteAmount() {

        boolean isContinue = true;
        long amount = 0L;
        do {
            try {
                amount = Long.parseLong(terminalService.readStringWithPrompt("Enter number of banknotes (or 0 to exit): "));
                isContinue = false;
            } catch (NumberFormatException e) {
                terminalService.printLine("You entered wrong number");
            }
            if (amount == 0) {
                isContinue = false;
            }
        } while (isContinue);
        return amount;
    }

    @Override
    public MenuOption getProcessedCommandOption() {
        return processedCommandOption;
    }

}