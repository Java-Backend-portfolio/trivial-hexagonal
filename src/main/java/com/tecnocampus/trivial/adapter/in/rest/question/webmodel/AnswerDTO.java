package com.tecnocampus.trivial.adapter.in.rest.question.webmodel;

import com.tecnocampus.trivial.model.Answer;

public record AnswerDTO(String description, boolean correct) {

    public static AnswerDTO fromDomainModel(Answer answer) {
        return new AnswerDTO(answer.getDescription(), answer.isCorrect());
    }

}
