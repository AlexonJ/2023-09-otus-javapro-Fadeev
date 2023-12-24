package ru.otus.javapro.homeworks.hw15springdata.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.javapro.homeworks.hw15springdata.dtos.ReviewDto;
import ru.otus.javapro.homeworks.hw15springdata.dtos.requests.CreateOrUpdateReviewDtoRq;
import ru.otus.javapro.homeworks.hw15springdata.dtos.responses.CreateOrUpdateReviewDtoRs;
import ru.otus.javapro.homeworks.hw15springdata.mappers.DtoMapper;
import ru.otus.javapro.homeworks.hw15springdata.services.ReviewsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewsController {

    private final ReviewsService reviewsService;

    private final DtoMapper mapper;

    @Autowired
    public ReviewsController(ReviewsService reviewsService, DtoMapper mapper) {
        this.reviewsService = reviewsService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/{bookId}")
    public ResponseEntity<List<ReviewDto>> getAllReviewsForBook(@PathVariable(name = "bookId") Long bookId) {
        return new ResponseEntity<>(reviewsService.getAllForBookId(bookId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateOrUpdateReviewDtoRs> createNewReview(@RequestBody CreateOrUpdateReviewDtoRq review) {
        return new ResponseEntity<>(mapper.reviewDtoToCreateOrUpdateReviewDtoRs(
                reviewsService.addReview(mapper.createOrUpdateReviewDtoRqToReviewDto(review))), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CreateOrUpdateReviewDtoRs> updateExistingReview(
            @PathVariable(name = "id") Long id,
            @RequestBody CreateOrUpdateReviewDtoRq review) {
        ReviewDto reviewDto = mapper.createOrUpdateReviewDtoRqToReviewDto(review);
        reviewDto.setId(id);
        return new ResponseEntity<>(
                mapper.reviewDtoToCreateOrUpdateReviewDtoRs(reviewsService.updateReview(reviewDto)), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteReview (@PathVariable(name = "id") Long id) {
        reviewsService.deleteReviewById(id);
    }
}
