package com.tecnocampus.trivial.application.port.out.persistence;

import com.tecnocampus.trivial.model.Question;

import java.util.List;

public interface QuestionRepository {

    void save(Question question);

    List<Question> findByCategoryAndTypeAndAmountByNumberPage(Integer category, String type, Integer amount, Integer currentPage);
}
