package ru.otus.javapro.homeworks.hw15springdata.dtos;

import lombok.Data;
import ru.otus.javapro.homeworks.hw15springdata.entities.Genre;

@Data
public class DetailedBookDto {

    private Long id;
    private String title;
    private Genre genre;
    private String authorName;
    private String description;
    private Float averageScore;

}