package ru.otus.javapro.homeworks.hw20webflux.reactive.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsDto {
    private Long id;
    private String name;
    private String description;
}
