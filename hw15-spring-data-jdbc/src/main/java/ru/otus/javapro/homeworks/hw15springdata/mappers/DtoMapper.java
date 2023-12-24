package ru.otus.javapro.homeworks.hw15springdata.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.javapro.homeworks.hw15springdata.dtos.BookDto;
import ru.otus.javapro.homeworks.hw15springdata.dtos.CategoryDto;
import ru.otus.javapro.homeworks.hw15springdata.dtos.DetailedBookDto;
import ru.otus.javapro.homeworks.hw15springdata.dtos.ReviewDto;
import ru.otus.javapro.homeworks.hw15springdata.dtos.requests.CreateOrUpdateReviewDtoRq;
import ru.otus.javapro.homeworks.hw15springdata.dtos.responses.CreateOrUpdateReviewDtoRs;
import ru.otus.javapro.homeworks.hw15springdata.entities.Book;
import ru.otus.javapro.homeworks.hw15springdata.entities.Category;
import ru.otus.javapro.homeworks.hw15springdata.entities.Review;

@Mapper(componentModel = "spring")
public interface DtoMapper {

    CategoryDto categoryToCategoryDto(Category category);

    BookDto bookToBookDto(Book book);

    ReviewDto ReviewToReviewDto(Review review);
    Review ReviewDtoToReview(ReviewDto review);

    CreateOrUpdateReviewDtoRs reviewDtoToCreateOrUpdateReviewDtoRs (ReviewDto review);
    @Mapping(target = "id", ignore = true)
    ReviewDto createOrUpdateReviewDtoRqToReviewDto (CreateOrUpdateReviewDtoRq createOrUpdateReviewDtoRq);
}
