package ru.otus.javapro.homeworks.hw15springdata.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.otus.javapro.homeworks.hw15springdata.entities.Book;

@Repository
public interface BooksPagingRepository extends PagingAndSortingRepository<Book, Long> {
}
