package ru.otus.javapro.homeworks.hw15springdata.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageDto<T> {

    private Integer pageNumber;

    private Integer pageSize;

    private Integer totalPages;

    private Long totalElements;

    private List<T> content;

}
