package ru.otus.javapro.homeworks.hw15springdata.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.javapro.homeworks.hw15springdata.dtos.DetailedBookDto;
import ru.otus.javapro.homeworks.hw15springdata.entities.Book;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BooksRepository extends ListCrudRepository<Book, Long> {
    @Query(value =
            "select b.id, b.title, b.genre, a.full_name as author_name, bd.description from BOOKS b " +
                    " left join AUTHORS a on b.author_id = a.id " +
                    " left join BOOKS_DETAILS bd on bd.book_id = b.id"
    )
    List<DetailedBookDto> findAllDetailedBooks();

    @Query(value =
            "SELECT b.id, b.title, b.genre, a.full_name as author_name, bd.description, AVG(r.score) as average_score " +
            "FROM BOOKS b " +
            "LEFT JOIN AUTHORS a ON b.author_id = a.id " +
            "LEFT JOIN BOOKS_DETAILS bd ON bd.book_id = b.id " +
            "LEFT JOIN REVIEWS r ON r.book_id = b.id AND r.review_date >= :lastMonth " +
            "GROUP BY b.id, b.title, b.genre, a.full_name, bd.description " +
            "ORDER BY " +
            "   CASE WHEN :sortField = 'id' AND :directionAsc THEN b.id END ASC, " +
            "   CASE WHEN :sortField = 'id' AND NOT :directionAsc THEN b.id END DESC, " +
            "   CASE WHEN :sortField = 'average_score' AND :directionAsc THEN AVG(r.score) END ASC, " +
            "   CASE WHEN :sortField = 'average_score' AND NOT :directionAsc THEN AVG(r.score) END DESC, " +
            "   CASE WHEN :sortField = 'title' AND :directionAsc THEN b.title END ASC, " +
            "   CASE WHEN :sortField = 'title' AND NOT :directionAsc THEN b.title END DESC, " +
            "   b.id ASC " +
            "LIMIT :#{#pageable.pageSize} OFFSET :#{#pageable.offset}"
    )
    List<DetailedBookDto> findAllDetailedBooksWithPagination(
            @Param("lastMonth") LocalDate lastMonth,
            @Param("sortField") String sortField,
            @Param("directionAsc") boolean direction,
            @Param("pageable") Pageable pageable);

    @Query("update books set title = :title where id = :id")
    @Modifying
    void changeTitleById(Long id, String title);
}