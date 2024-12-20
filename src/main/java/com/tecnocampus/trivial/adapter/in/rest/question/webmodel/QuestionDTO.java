package com.tecnocampus.trivial.adapter.in.rest.question.webmodel;

import com.tecnocampus.trivial.model.Question;
import java.util.List;

public record QuestionDTO(String category, String difficulty, List<AnswerDTO> answers, String question, String type) {

    public static QuestionDTO fromDomainModel(Question question) {
        List<AnswerDTO> answers = question.getAnswers().stream().map(AnswerDTO::fromDomainModel).toList();
        return new QuestionDTO(question.getCategory().getTitle(), question.getDificult(),
                               answers, question.getQuestion(), question.getType());
    }
}
