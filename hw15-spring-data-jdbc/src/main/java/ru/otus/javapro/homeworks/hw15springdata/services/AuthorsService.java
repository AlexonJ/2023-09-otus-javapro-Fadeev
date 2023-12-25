package ru.otus.javapro.homeworks.hw15springdata.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.javapro.homeworks.hw15springdata.entities.Author;
import ru.otus.javapro.homeworks.hw15springdata.repositories.AuthorsRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorsService {
    private final AuthorsRepository authorsRepository;

    public Optional<Author> findById(@PathVariable Long id) {
        return authorsRepository.findById(id);
    }
}
