package ru.otus.javapro.homeworks.hw15springdata.entities;

public enum Genre {
    FANTASY("Фэнтези"), SCIFI("Научная фантастика");

    private String name;

    public String getName() {
        return name;
    }

    Genre(String name) {
        this.name = name;
    }
}
