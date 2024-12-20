package com.tecnocampus.trivial.adapter.out.persistence.jpa.mapper;

import com.tecnocampus.trivial.adapter.out.persistence.jpa.entity.AnswerJpaEntity;
import com.tecnocampus.trivial.model.Answer;

import java.util.Set;
import java.util.stream.Collectors;

public class AnswerMapper {

    static Answer toModelEntity(AnswerJpaEntity jpaEntity) {
        return new Answer(jpaEntity.getId(), jpaEntity.getDescription(), jpaEntity.isCorrect());
    }

    static AnswerJpaEntity toJpaEntity(Answer answer) {
        AnswerJpaEntity jpaEntity = new AnswerJpaEntity();

        jpaEntity.setId(answer.getId());
        jpaEntity.setDescription(answer.getDescription());
        jpaEntity.setCorrect(answer.isCorrect());

        return jpaEntity;
    }

    public static Set<AnswerJpaEntity> toModelEntities(Set<Answer> jpaEntities) {
        return jpaEntities.stream().map(AnswerMapper::toJpaEntity).collect(Collectors.toSet());
    }
}
