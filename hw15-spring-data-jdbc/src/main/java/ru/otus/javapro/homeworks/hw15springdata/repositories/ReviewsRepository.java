package ru.otus.javapro.homeworks.hw15springdata.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.javapro.homeworks.hw15springdata.entities.Review;

import java.util.List;

@Repository
public interface ReviewsRepository extends CrudRepository<Review, Long> {

    List<Review> findAllByBookId(Long bookId);

}
