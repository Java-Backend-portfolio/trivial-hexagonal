package com.tecnocampus.trivial.adapter.out.persistence.jpa.mapper;

import com.tecnocampus.trivial.adapter.out.persistence.jpa.entity.CategoryJpaEntity;
import com.tecnocampus.trivial.model.Category;
import java.util.List;

public class CategoryMapper {

    public static CategoryJpaEntity toJpaEntity(Category category) {
        CategoryJpaEntity jpaEntity = new CategoryJpaEntity();

        jpaEntity.setId(category.getId());
        jpaEntity.setTitle(category.getTitle());

        return jpaEntity;
    }

    static Category toModelEntity(CategoryJpaEntity jpaEntity) {
        return new Category(jpaEntity.getId(), jpaEntity.getTitle());
    }

    public static List<Category> toModelEntities(List<CategoryJpaEntity> jpaEntities) {
        return jpaEntities.stream().map(CategoryMapper::toModelEntity).toList();
    }

}
