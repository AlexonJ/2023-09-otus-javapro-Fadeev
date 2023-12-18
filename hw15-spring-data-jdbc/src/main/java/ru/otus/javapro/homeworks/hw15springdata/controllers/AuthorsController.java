package ru.otus.javapro.homeworks.hw15springdata.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.javapro.homeworks.hw15springdata.dtos.AuthorDto;
import ru.otus.javapro.homeworks.hw15springdata.entities.Author;
import ru.otus.javapro.homeworks.hw15springdata.exceptions.ResourceNotFoundException;
import ru.otus.javapro.homeworks.hw15springdata.services.AuthorsService;

import java.util.function.Function;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorsController {
    private final AuthorsService authorsService;

    private static final Function<Author, AuthorDto> MAP_TO_DTO_FUNCTION = a -> new AuthorDto(a.getId(), a.getFullName());

    @Autowired
    public AuthorsController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @GetMapping("/{id}")
    public AuthorDto findById(@PathVariable Long id) {
        return authorsService.findById(id).map(MAP_TO_DTO_FUNCTION).orElseThrow(() -> new ResourceNotFoundException("Автор не найден"));
    }
}
