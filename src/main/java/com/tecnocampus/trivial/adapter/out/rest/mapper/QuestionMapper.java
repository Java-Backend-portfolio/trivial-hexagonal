package com.tecnocampus.trivial.adapter.out.rest.mapper;

import com.tecnocampus.trivial.adapter.out.rest.entityDTO.QuestionApiDTO;
import com.tecnocampus.trivial.model.Answer;
import com.tecnocampus.trivial.model.Category;
import com.tecnocampus.trivial.model.Question;

import java.util.List;

public class QuestionMapper {

    static Question toModelEntity(QuestionApiDTO apiDTOEntity) {
        Question question = new Question(apiDTOEntity.difficulty(),
                                         apiDTOEntity.question(),
                                         apiDTOEntity.type());

        question.setCategory(new Category(apiDTOEntity.category()));

        apiDTOEntity.incorrect_answers().stream()
                                        .map(a -> new Answer(a, false))
                                        .forEach(question::addAnswer);

        question.addAnswer(new Answer(apiDTOEntity.correct_answer(), true));

        return question;
    }

    public static List<Question> toModelEntities(List<QuestionApiDTO> apiDTOEntities) {
        return apiDTOEntities.stream().map(QuestionMapper::toModelEntity).toList();
    }
}
