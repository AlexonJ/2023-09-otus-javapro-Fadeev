package ru.otus.javapro.homeworks.hw15springdata.dtos.requests;

import lombok.Data;

@Data
public class CreateOrUpdateCategoryDtoRq {
    private Long id;
    private String title;
}
