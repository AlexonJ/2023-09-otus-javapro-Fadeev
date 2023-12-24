package ru.otus.javapro.homeworks.hw15springdata.controllers;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.javapro.homeworks.hw15springdata.dtos.BookDto;
import ru.otus.javapro.homeworks.hw15springdata.dtos.DetailedBookDto;
import ru.otus.javapro.homeworks.hw15springdata.dtos.PageDto;
import ru.otus.javapro.homeworks.hw15springdata.dtos.ReviewDto;
import ru.otus.javapro.homeworks.hw15springdata.services.BooksService;
import ru.otus.javapro.homeworks.hw15springdata.services.ReviewsService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/books")
public class BooksController {

    private final BooksService booksService;
    private final ReviewsService reviewsService;

    @GetMapping(path = "/detailed")
    public PageDto<DetailedBookDto> findAllDetailedBooksPaged(
            @RequestParam(name = "pageNumber", defaultValue = "0") @Min(0) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10")  @Min(1) @Max(100) Integer pageSize,
            @RequestParam(name = "sortingField", defaultValue = "title", required = false)  String sortingField,
            @RequestParam(name = "sortingDirection", defaultValue = "ASC", required = false)  String sortingDirection) {
        return booksService.findAllDetailedBooksPaged(PageRequest.of(pageNumber, pageSize,
                sortingDirection.equalsIgnoreCase("ASC") ? Sort.by(sortingField).ascending() : Sort.by(sortingField).descending()));
    }

    @GetMapping
    public PageDto<BookDto> findAllBooksPaged(
            @RequestParam(name = "pageNumber", defaultValue = "0") @Min(0) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10")  @Min(1) @Max(100) Integer pageSize,
            @RequestParam(name = "sortingField", defaultValue = "title", required = false)  String sortingField,
            @RequestParam(name = "sortingDirection", defaultValue = "ASC", required = false)  String sortingDirection) {
        return booksService.findAllBooksPaged(PageRequest.of(pageNumber, pageSize
                ,sortingDirection.equalsIgnoreCase("ASC") ? Sort.by(sortingField).ascending() : Sort.by(sortingField).descending()
        ));
    }

    @GetMapping(path = "/top10")
    public List<DetailedBookDto> getTop10Books() {
        return booksService.findAllDetailedBooksPaged(PageRequest.of(0, 10,
                Sort.by("average_score").descending())).getContent();
    }

    @PatchMapping("/{id}/title")
    public void updateTitleById(@PathVariable Long id, @RequestParam String value) {
        booksService.updateTitleById(id, value);
    }

    @GetMapping(value = "/{id}/reviews")
    public ResponseEntity<List<ReviewDto>> getAllReviewsForBook(@PathVariable(name = "id") Long bookId) {
        return new ResponseEntity<>(reviewsService.getAllForBookId(bookId), HttpStatus.OK);
    }
}
