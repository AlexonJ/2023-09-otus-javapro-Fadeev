package ru.otus.javapro.homeworks.hw15springdata.entities;

public enum Genre {
    SCIFI("Science Fiction"),
    MYSTERY("Mystery"),
    DYSTOPIAN("Dystopian"),
    POLITICAL("Political"),
    HORROR("Horror"),
    CLASSIC_FICTION("Classic Fiction"),
    ROMANCE("Romance"),
    ADVENTURE("Adventure"),
    FANTASY("Fantasy"),
    SCIENCE("Science");

    private String name;

    public String getName() {
        return name;
    }

    Genre(String name) {
        this.name = name;
    }
}
