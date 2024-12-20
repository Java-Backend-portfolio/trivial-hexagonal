package com.tecnocampus.trivial.adapter.out.persistence.jpa.mapper;

import com.tecnocampus.trivial.adapter.out.persistence.jpa.entity.AnswerJpaEntity;
import com.tecnocampus.trivial.adapter.out.persistence.jpa.entity.QuestionJpaEntity;
import com.tecnocampus.trivial.model.Question;

import java.util.List;
import java.util.Set;

public class QuestionMapper {

    public static QuestionJpaEntity toJpaEntity(Question question) {
        QuestionJpaEntity jpaEntity = new QuestionJpaEntity();

        jpaEntity.setId(question.getId());
        jpaEntity.setDificult(question.getDificult());
        jpaEntity.setCategory(CategoryMapper.toJpaEntity(question.getCategory()));
        jpaEntity.setQuestionDescription(question.getQuestion());
        jpaEntity.setType(question.getType());

        Set<AnswerJpaEntity> answers = AnswerMapper.toModelEntities(question.getAnswers());

        answers.stream().forEach(a -> jpaEntity.addAnswer(a));

        return jpaEntity;
    }

    private static Question toModelEntity(QuestionJpaEntity jpaEntity) {
        Question question = new Question(jpaEntity.getId(),
                                         jpaEntity.getDificult(),
                                         jpaEntity.getQuestionDescription(),
                                         jpaEntity.getType());

        question.setCategory(CategoryMapper.toModelEntity(jpaEntity.getCategory()));

        jpaEntity.getAnswers().stream()
                              .map(AnswerMapper::toModelEntity)
                              .forEach(question::addAnswer);

        return question;
    }

    public static List<Question> toModelEntities(List<QuestionJpaEntity> jpaEntities) {
        return jpaEntities.stream().map(QuestionMapper::toModelEntity).toList();
    }
}
