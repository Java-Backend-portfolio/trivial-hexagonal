package com.tecnocampus.trivial.application.port.in.category;

import com.tecnocampus.trivial.model.Category;

import java.util.List;

public interface FindAllCategoriesUseCase {

    List<Category> findAll();
}
