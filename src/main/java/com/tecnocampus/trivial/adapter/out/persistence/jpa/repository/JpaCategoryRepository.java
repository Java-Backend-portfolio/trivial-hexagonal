package com.tecnocampus.trivial.adapter.out.persistence.jpa.repository;

import com.tecnocampus.trivial.adapter.out.persistence.jpa.entity.CategoryJpaEntity;
import com.tecnocampus.trivial.adapter.out.persistence.jpa.mapper.CategoryMapper;
import com.tecnocampus.trivial.adapter.out.persistence.jpa.springDataRepository.JpaCategorySpringDataRepository;
import com.tecnocampus.trivial.application.port.out.persistence.CategoryRepository;
import com.tecnocampus.trivial.model.Category;
import jakarta.transaction.Transactional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import java.util.List;

@ConditionalOnProperty(name = "persistence", havingValue = "mysql")
@Repository
public class JpaCategoryRepository implements CategoryRepository {

    private final JpaCategorySpringDataRepository springDataRepository;

    public JpaCategoryRepository(JpaCategorySpringDataRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public void save(Category category) {
        springDataRepository.save(CategoryMapper.toJpaEntity(category));
    }

    @Override
    @Transactional
    public List<Category> findAll() {
        List<CategoryJpaEntity> jpaCategorys = springDataRepository.findAll();
        return CategoryMapper.toModelEntities(jpaCategorys);
    }
}
