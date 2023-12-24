package ru.otus.javapro.homeworks.hw15springdata.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.javapro.homeworks.hw15springdata.dtos.CategoryDto;
import ru.otus.javapro.homeworks.hw15springdata.dtos.requests.CreateOrUpdateCategoryDtoRq;
import ru.otus.javapro.homeworks.hw15springdata.entities.Category;
import ru.otus.javapro.homeworks.hw15springdata.mappers.DtoMapper;
import ru.otus.javapro.homeworks.hw15springdata.repositories.CategoriesRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;
    private final DtoMapper mapper;

    public List<Category> findAll() {
        return categoriesRepository.findAll();
    }

    public Optional<Category> findByTitle(String title) {
        return categoriesRepository.findByTitle(title);
    }

    public Optional<Category> findById(Long id) {
        return categoriesRepository.findById(id);
    }

    public void deleteById(Long id) {
        categoriesRepository.deleteById(id);
    }

    public CategoryDto createNewCategory(CreateOrUpdateCategoryDtoRq createOrUpdateCategoryDtoRq) {
        Category newCategory = new Category(createOrUpdateCategoryDtoRq.getId(), createOrUpdateCategoryDtoRq.getTitle());
        return mapper.categoryToCategoryDto(categoriesRepository.save(newCategory));
    }

    public CategoryDto fullUpdateCategory(CreateOrUpdateCategoryDtoRq createOrUpdateCategoryDtoRq) {
        Category updatedCategory = new Category(createOrUpdateCategoryDtoRq.getId(), createOrUpdateCategoryDtoRq.getTitle());
        return mapper.categoryToCategoryDto(categoriesRepository.save(updatedCategory));
    }
}
