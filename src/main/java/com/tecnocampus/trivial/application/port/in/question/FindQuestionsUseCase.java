package com.tecnocampus.trivial.application.port.in.question;

import com.tecnocampus.trivial.model.Question;

import java.util.List;

public interface FindQuestionsUseCase {

    List<Question> findCategoryAndTypeAndAmount(Integer category, String type, int amount);
}
