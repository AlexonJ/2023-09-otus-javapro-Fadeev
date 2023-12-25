package ru.otus.javapro.homeworks.hw15springdata.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.javapro.homeworks.hw15springdata.dtos.CategoryDto;
import ru.otus.javapro.homeworks.hw15springdata.dtos.requests.CreateOrUpdateCategoryDtoRq;
import ru.otus.javapro.homeworks.hw15springdata.dtos.SimplestPageDto;
import ru.otus.javapro.homeworks.hw15springdata.entities.Category;
import ru.otus.javapro.homeworks.hw15springdata.exceptions.ResourceNotFoundException;
import ru.otus.javapro.homeworks.hw15springdata.services.CategoriesService;

import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoriesController {
    private final CategoriesService categoriesService;

    private static final Function<Category, CategoryDto> MAP_TO_DTO_FUNCTION = c -> new CategoryDto(c.getId(), c.getTitle());

    @Autowired
    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping
    public SimplestPageDto<CategoryDto> findAll() {
        return new SimplestPageDto<>(categoriesService.findAll().stream().map(MAP_TO_DTO_FUNCTION).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id) {
        return categoriesService.findById(id).map(MAP_TO_DTO_FUNCTION).orElseThrow(() -> new ResourceNotFoundException("Категория не найдена"));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        categoriesService.deleteById(id);
    }

    @PostMapping
    public CategoryDto createNewCategory(@RequestBody CreateOrUpdateCategoryDtoRq createOrUpdateCategoryDtoRq) {
        return categoriesService.createNewCategory(createOrUpdateCategoryDtoRq);
    }

    @PutMapping
    public CategoryDto updateCategory(@RequestBody CreateOrUpdateCategoryDtoRq createOrUpdateCategoryDtoRq) {
        return categoriesService.fullUpdateCategory(createOrUpdateCategoryDtoRq);
    }
}
