package ru.otus.javapro.homeworks.hw15springdata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.otus.javapro.homeworks.hw15springdata.dtos.BookDto;
import ru.otus.javapro.homeworks.hw15springdata.dtos.DetailedBookDto;
import ru.otus.javapro.homeworks.hw15springdata.dtos.PageDto;
import ru.otus.javapro.homeworks.hw15springdata.entities.Book;
import ru.otus.javapro.homeworks.hw15springdata.mappers.DtoMapper;
import ru.otus.javapro.homeworks.hw15springdata.repositories.BooksPagingRepository;
import ru.otus.javapro.homeworks.hw15springdata.repositories.BooksRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class BooksService {
    private final BooksRepository booksRepository;

    private final BooksPagingRepository booksPagedRepository;

    private final DtoMapper mapper;

    @Autowired
    public BooksService(BooksRepository booksRepository, BooksPagingRepository booksPagingRepository, DtoMapper mapper) {
        this.booksRepository = booksRepository;
        this.booksPagedRepository = booksPagingRepository;
        this.mapper = mapper;
    }

    public PageDto<DetailedBookDto> findAllDetailedBooksPaged(Pageable pageable) {

        List<DetailedBookDto> contentList = booksRepository.findAllDetailedBooksWithPagination(
                LocalDate.now().minusMonths(1),
                pageable.getSort().get().findFirst().map(Sort.Order::getProperty).orElse(""),
                pageable.getSort().get().findFirst().map(order -> order.getDirection().isAscending()).orElse(false),
                pageable);

        long totalElements = booksRepository.count();
        int totalPages = (int) Math.ceil((double) totalElements / pageable.getPageSize());

        return new PageDto<>(pageable.getPageNumber(), pageable.getPageSize(), totalPages,
                totalElements, contentList);

    }

    public PageDto<BookDto> findAllBooksPaged(Pageable pageable) {
        Page<Book> pagedList = booksPagedRepository.findAll(pageable);
        return new PageDto<>(pageable.getPageNumber(), pageable.getPageSize(), pagedList.getTotalPages(),
                pagedList.getTotalElements(), pagedList.getContent().stream().map(mapper::bookToBookDto).toList());
    }


    public void updateTitleById(Long id, String newTitle) {
        booksRepository.changeTitleById(id, newTitle);
    }
}
