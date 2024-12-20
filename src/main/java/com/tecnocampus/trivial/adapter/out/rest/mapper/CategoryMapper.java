package com.tecnocampus.trivial.adapter.out.rest.mapper;

import com.tecnocampus.trivial.adapter.out.rest.entityDTO.CategoryApiDTO;
import com.tecnocampus.trivial.model.Category;

import java.util.List;

public class CategoryMapper {

    static Category toModelEntity(CategoryApiDTO apiDTOEntity) {
        return new Category(apiDTOEntity.id(), apiDTOEntity.name());
    }

    public static List<Category> toModelEntities(List<CategoryApiDTO> apiDTOEntities) {
        return apiDTOEntities.stream().map(CategoryMapper::toModelEntity).toList();
    }

}
