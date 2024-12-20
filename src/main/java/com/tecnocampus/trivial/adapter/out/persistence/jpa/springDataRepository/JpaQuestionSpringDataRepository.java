package com.tecnocampus.trivial.adapter.out.persistence.jpa.springDataRepository;

import com.tecnocampus.trivial.adapter.out.persistence.jpa.entity.QuestionJpaEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaQuestionSpringDataRepository extends JpaRepository<QuestionJpaEntity, Integer> {

    @Query("SELECT q FROM QuestionJpaEntity q WHERE q.category.id = :categoryId AND q.type = :type")
    List<QuestionJpaEntity> findByCategoryAndType(@Param("categoryId") Integer categoryId,
                                                 @Param("type") String type,
                                                 Pageable pageable);
}
