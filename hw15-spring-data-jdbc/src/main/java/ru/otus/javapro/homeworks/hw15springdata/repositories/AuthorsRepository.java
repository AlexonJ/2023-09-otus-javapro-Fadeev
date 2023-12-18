package ru.otus.javapro.homeworks.hw15springdata.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.javapro.homeworks.hw15springdata.entities.Author;

@Repository
public interface AuthorsRepository extends ListCrudRepository<Author, Long> {
}