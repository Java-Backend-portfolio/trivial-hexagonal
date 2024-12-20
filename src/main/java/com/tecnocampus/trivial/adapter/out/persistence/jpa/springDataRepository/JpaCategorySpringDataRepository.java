package com.tecnocampus.trivial.adapter.out.persistence.jpa.springDataRepository;

import com.tecnocampus.trivial.adapter.out.persistence.jpa.entity.CategoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCategorySpringDataRepository extends JpaRepository<CategoryJpaEntity, Integer> {
}
