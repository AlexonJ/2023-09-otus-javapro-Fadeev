package ru.otus.javapro.homeworks.hw15springdata.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.javapro.homeworks.hw15springdata.dtos.AuthorDto;
import ru.otus.javapro.homeworks.hw15springdata.entities.Author;
import ru.otus.javapro.homeworks.hw15springdata.exceptions.ResourceNotFoundException;
import ru.otus.javapro.homeworks.hw15springdata.services.AuthorsService;

import java.util.function.Function;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/authors")
public class AuthorsController {
    private final AuthorsService authorsService;

    private static final Function<Author, AuthorDto> MAP_TO_DTO_FUNCTION = a -> new AuthorDto(a.getId(), a.getFullName());

    @GetMapping("/{id}")
    public AuthorDto findById(@PathVariable Long id) {
        return authorsService.findById(id).map(MAP_TO_DTO_FUNCTION).orElseThrow(() -> new ResourceNotFoundException("Автор не найден"));
    }
}
