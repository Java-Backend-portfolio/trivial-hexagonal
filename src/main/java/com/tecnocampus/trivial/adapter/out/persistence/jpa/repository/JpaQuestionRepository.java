package com.tecnocampus.trivial.adapter.out.persistence.jpa.repository;

import com.tecnocampus.trivial.adapter.out.persistence.jpa.entity.CategoryJpaEntity;
import com.tecnocampus.trivial.adapter.out.persistence.jpa.entity.QuestionJpaEntity;
import com.tecnocampus.trivial.adapter.out.persistence.jpa.mapper.QuestionMapper;
import com.tecnocampus.trivial.adapter.out.persistence.jpa.springDataRepository.JpaAnswerSpringDataRepository;
import com.tecnocampus.trivial.adapter.out.persistence.jpa.springDataRepository.JpaCategorySpringDataRepository;
import com.tecnocampus.trivial.adapter.out.persistence.jpa.springDataRepository.JpaQuestionSpringDataRepository;
import com.tecnocampus.trivial.application.port.out.persistence.QuestionRepository;
import com.tecnocampus.trivial.model.Question;
import jakarta.transaction.Transactional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ConditionalOnProperty(name = "persistence", havingValue = "mysql")
@Repository
public class JpaQuestionRepository implements QuestionRepository {

    private final JpaQuestionSpringDataRepository questionSDR;
    private final JpaCategorySpringDataRepository categorySDR;
    private final JpaAnswerSpringDataRepository answerSDR;

    public JpaQuestionRepository(JpaQuestionSpringDataRepository questionSDR, JpaCategorySpringDataRepository categorySDR, JpaAnswerSpringDataRepository answerSDR) {
        this.questionSDR = questionSDR;
        this.categorySDR = categorySDR;
        this.answerSDR = answerSDR;
    }

    @Override
    public void save(Question question) {
        questionSDR.save(QuestionMapper.toJpaEntity(question));
    }

    @Override
    @Transactional
    public List<Question> findByCategoryAndTypeAndAmountByNumberPage(Integer categoryId, String type, Integer amount, Integer currentPage) {
        Optional<CategoryJpaEntity> categoryJpaEntity = categorySDR.findById(categoryId);

        Pageable pageable = PageRequest.of(currentPage, amount);

        if (categoryJpaEntity.isPresent()) {
            List<QuestionJpaEntity> questions = questionSDR.findByCategoryAndType(categoryId, type, pageable);
            return QuestionMapper.toModelEntities(questions);
        } else {
            return new ArrayList<>();
        }
    }
}
