package ru.otus.javapro.homeworks.hw15springdata.services;

import org.springframework.stereotype.Service;
import ru.otus.javapro.homeworks.hw15springdata.dtos.ReviewDto;
import ru.otus.javapro.homeworks.hw15springdata.exceptions.ResourceNotFoundException;
import ru.otus.javapro.homeworks.hw15springdata.mappers.DtoMapper;
import ru.otus.javapro.homeworks.hw15springdata.repositories.BooksRepository;
import ru.otus.javapro.homeworks.hw15springdata.repositories.ReviewsRepository;

import java.util.List;

@Service
public class ReviewsService {

    private final ReviewsRepository reviewsRepository;
    private final BooksRepository booksRepository;
    private final DtoMapper mapper;

    public ReviewsService(ReviewsRepository reviewsRepository, BooksRepository booksRepository, DtoMapper mapper) {
        this.reviewsRepository = reviewsRepository;
        this.booksRepository = booksRepository;
        this.mapper = mapper;
    }

    public List<ReviewDto> getAllForBookId(Long bookId) {
        booksRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book with id %s not found".formatted(bookId)));
        return reviewsRepository.findAllByBookId(bookId).stream().map(mapper::ReviewToReviewDto).toList();
    }

    public ReviewDto addReview(ReviewDto reviewDto) {
        return mapper.ReviewToReviewDto(reviewsRepository.save(mapper.ReviewDtoToReview(reviewDto)));
    }

    public ReviewDto updateReview(ReviewDto reviewDto) {
        reviewsRepository.findById(reviewDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Review with id %s not found".formatted(reviewDto.getId())));
        return mapper.ReviewToReviewDto(reviewsRepository.save(mapper.ReviewDtoToReview(reviewDto)));
    }

    public void deleteReviewById(Long id) {
        reviewsRepository.deleteById(id);
    }

}
