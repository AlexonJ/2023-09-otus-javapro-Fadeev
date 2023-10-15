package ru.otus.javapro.homeworks.atmemulator.services;

import ru.otus.javapro.homeworks.atmemulator.domain.BanknoteStackImpl;
import ru.otus.javapro.homeworks.atmemulator.domain.MoneyRub;
import ru.otus.javapro.homeworks.atmemulator.menu.MenuOption;
import ru.otus.javapro.homeworks.atmemulator.menu.MenuOptionsRegistry;
import ru.otus.javapro.homeworks.atmemulator.menu.MenuOptionsRegistryImpl;
import ru.otus.javapro.homeworks.atmemulator.menu.processors.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AtmBuilder {

    public static AtmService build() {

        TerminalService terminalService = new TerminalServiceImpl(System.out, System.in);
        CashStorageService cashStorageService = new CashStorageServiceImpl(new BanknoteStackImpl(), new MoneyRub());
        AtmStopService atmStopService = new AtmStopServiceImpl();

        List<MenuSingleCommandProcessor> mainMenuProcessors = getMainMenuSingleCommandProcessors(terminalService, cashStorageService, atmStopService);

        List<MenuOption> mainMenuOptions = mainMenuProcessors.stream().map(
                MenuSingleCommandProcessor::getProcessedCommandOption).sorted(Comparator.comparingInt(MenuOption::getId)).collect(Collectors.toList());
        MenuOptionsRegistryImpl mainMenuOptionsRegistry = new MenuOptionsRegistryImpl(mainMenuOptions);

        AtmService atmService = new AtmServiceImpl(
                terminalService,
                atmStopService,
                mainMenuOptionsRegistry,
                new MenuCommandsProcessorImpl(mainMenuProcessors));

        BanknoteStackImpl banknoteStack = new BanknoteStackImpl();
        banknoteStack.putBanknote(5000L,10L);
        banknoteStack.putBanknote(2000L,20L);
        banknoteStack.putBanknote(1000L,50L);
        banknoteStack.putBanknote(500L,100L);
        banknoteStack.putBanknote(100L,300L);

        cashStorageService.putBanknoteStack(banknoteStack);

        return atmService;
    }

    private static List<MenuSingleCommandProcessor> getMainMenuSingleCommandProcessors(TerminalService terminalService, CashStorageService cashStorageService, AtmStopService atmStopService) {
        MenuOptionsRegistry depositMenu = getDepositMenu(cashStorageService);
        return List.of(
                new CashWithdrawalSingleCommandProcessor(
                        new MenuOption(1, "Cash withdrawal"), terminalService, cashStorageService),
                new CashDepositSingleCommandProcessor(
                        new MenuOption(2, "Cash deposit"), terminalService, cashStorageService, depositMenu,depositMenu.getSize() - 1),
                new ShowBalanceSingleCommandProcessor(
                        new MenuOption(3, "Print balance"), terminalService, cashStorageService),
                new StopAtmSingleCommandProcessor(
                        new MenuOption(4, "Exit"), atmStopService, terminalService)
        );
    }

    private static MenuOptionsRegistry getDepositMenu(CashStorageService cashStorageService){
        List<Long> banknoteDenominations = cashStorageService.getBanknoteDenominations();
        List<MenuOption> menuOptionList = new ArrayList<>();
        for (int i = 1; i <= banknoteDenominations.size(); i++){
            menuOptionList.add(new MenuOption(i, banknoteDenominations.get(i-1).toString()));
        }
        menuOptionList.add(new MenuOption(banknoteDenominations.size() + 1, "Exit"));
        return new MenuOptionsRegistryImpl(menuOptionList);
    }
}
