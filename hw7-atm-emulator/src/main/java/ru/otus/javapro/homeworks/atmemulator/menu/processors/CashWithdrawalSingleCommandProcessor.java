package ru.otus.javapro.homeworks.atmemulator.menu.processors;

import lombok.AllArgsConstructor;
import ru.otus.javapro.homeworks.atmemulator.domain.BanknoteStack;
import ru.otus.javapro.homeworks.atmemulator.menu.MenuOption;
import ru.otus.javapro.homeworks.atmemulator.services.CashStorageService;
import ru.otus.javapro.homeworks.atmemulator.services.TerminalService;

@AllArgsConstructor
public class CashWithdrawalSingleCommandProcessor implements MenuSingleCommandProcessor {

    private final MenuOption processedCommandOption;
    private final TerminalService terminalService;
    private final CashStorageService cashStorageService;

    @Override
    public void processCommand() {
        long amount;
        do{
            amount = 0L;
            String amountString = terminalService.readStringWithPrompt("Enter amount to withdrawal or 0 to exit: ");
            try{
                amount = Long.parseLong(amountString);

                if (amount > cashStorageService.getTotalBalance()) {
                    terminalService.printLine("Entered amount is greater than the balance ");

                } else if (!cashStorageService.isAmountCanBeIssued(amount)) {
                    terminalService.printLine("Entered amount cannot be issued");
                } else if (amount != 0) {
                    BanknoteStack banknoteStackToIssue = cashStorageService.getBanknoteStackBySum(amount);
                    cashStorageService.takeBanknoteStack(banknoteStackToIssue);
                    terminalService.printFormattedLine("You got: " + banknoteStackToIssue);
                    terminalService.waitUntilEnterIsPressed();
                    break;
                }

            } catch (NumberFormatException e){
                terminalService.printLine("You entered a wrong number");
            }

        } while (amount != 0L);

    }

    @Override
    public MenuOption getProcessedCommandOption() {
        return processedCommandOption;
    }
}