package com.tecnocampus.trivial.application.service.category;

import com.tecnocampus.trivial.application.port.in.category.FindAllCategoriesUseCase;
import com.tecnocampus.trivial.application.port.out.persistence.CategoryRepository;
import com.tecnocampus.trivial.model.Category;

import java.util.List;

public class FindAllCategoriesService implements FindAllCategoriesUseCase {

    private final CategoryRepository categoryRepository;

    public FindAllCategoriesService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}

