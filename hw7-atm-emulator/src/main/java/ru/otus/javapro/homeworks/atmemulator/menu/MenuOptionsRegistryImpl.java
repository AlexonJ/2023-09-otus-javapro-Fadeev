package ru.otus.javapro.homeworks.atmemulator.menu;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MenuOptionsRegistryImpl implements MenuOptionsRegistry {
    private final Map<Integer, MenuOption> options;

    public MenuOptionsRegistryImpl(List<MenuOption> options) {
        this.options = options.stream()
                .collect(Collectors.toUnmodifiableMap(MenuOption::getId, Function.identity()));
    }

    @Override
    public List<MenuOption> getAvailableMenuOptions() {
        return options.values().stream().sorted(Comparator.comparingInt(MenuOption::getId)).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Optional<MenuOption> getMenuOptionById(int id) {
        return Optional.ofNullable(options.get(id));
    }

    @Override
    public Integer getSize() {
        return options.size();
    }
}
