package ru.otus.javapro.homeworks.hw15springdata.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Data
@Table("BOOKS")
public class Book {
    @Id
    private Long id;
    private String title;
    private Long authorId;
    private Genre genre;
    @MappedCollection(idColumn = "BOOK_ID")
    private BookDetails bookDetails;

    @MappedCollection(idColumn = "BOOK_ID")
    private List<Review> review;

    @PersistenceCreator
    public Book(Long id, String title, Long authorId, BookDetails bookDetails, List<Review> review) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.bookDetails = bookDetails;
        this.review = review;
    }
}
