package ru.otus.javapro.homeworks.atmemulator.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MenuOption {

    private final int id;
    private final String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuOption that = (MenuOption) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
