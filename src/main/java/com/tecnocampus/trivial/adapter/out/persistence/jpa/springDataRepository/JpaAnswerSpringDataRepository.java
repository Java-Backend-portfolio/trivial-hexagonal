package com.tecnocampus.trivial.adapter.out.persistence.jpa.springDataRepository;

import com.tecnocampus.trivial.adapter.out.persistence.jpa.entity.AnswerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAnswerSpringDataRepository extends JpaRepository <AnswerJpaEntity, Integer> {
}
