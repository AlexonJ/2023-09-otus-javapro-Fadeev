package ru.otus.javapro.homeworks.hw15springdata.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("BOOKS_DETAILS")
public class BookDetails {
    @Id
    private Long id;
    private Long bookId;
    private String description;

    @PersistenceCreator
    public BookDetails(Long id, Long bookId, String description) {
        this.id = id;
        this.bookId = bookId;
        this.description = description;
    }
}
