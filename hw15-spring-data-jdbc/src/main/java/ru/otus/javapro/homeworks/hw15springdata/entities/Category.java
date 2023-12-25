package ru.otus.javapro.homeworks.hw15springdata.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("CATEGORIES")
public class Category {
    @Id
    private Long id;
    private String title;

    @PersistenceCreator
    public Category(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
