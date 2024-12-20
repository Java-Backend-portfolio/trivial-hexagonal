package com.tecnocampus.trivial.adapter.out.persistence.jpa.repository;

import com.tecnocampus.trivial.adapter.out.persistence.jpa.springDataRepository.JpaAnswerSpringDataRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

@ConditionalOnProperty(name = "persistence", havingValue = "mysql")
@Repository
public class JpaAnswerRepository {

    private final JpaAnswerSpringDataRepository springDataRepository;

    public JpaAnswerRepository(JpaAnswerSpringDataRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }
}
