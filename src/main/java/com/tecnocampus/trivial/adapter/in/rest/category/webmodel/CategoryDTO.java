package com.tecnocampus.trivial.adapter.in.rest.category.webmodel;

import com.tecnocampus.trivial.model.Category;

public record CategoryDTO(Integer id, String name) {

    public static CategoryDTO fromDomainModel(Category category) {
        return new CategoryDTO(category.getId(), category.getTitle());
    }
}
