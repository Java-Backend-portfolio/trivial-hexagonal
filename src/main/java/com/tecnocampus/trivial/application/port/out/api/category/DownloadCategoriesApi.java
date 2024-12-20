package com.tecnocampus.trivial.application.port.out.api.category;

import com.tecnocampus.trivial.model.Category;

import java.util.List;

public interface DownloadCategoriesApi {

    List<Category> downloadCategories();
}
