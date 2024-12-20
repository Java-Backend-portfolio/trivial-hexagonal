package com.tecnocampus.trivial.application.service.question;

import com.tecnocampus.trivial.application.port.in.question.FindQuestionsUseCase;
import com.tecnocampus.trivial.application.port.out.persistence.QuestionRepository;
import com.tecnocampus.trivial.application.service.categorypage.CategoryPageService;
import com.tecnocampus.trivial.model.Question;

import java.util.List;

public class FindQuestionsService implements FindQuestionsUseCase {

    private final QuestionRepository questionRepository;
    private final CategoryPageService categoryPageService;

    public FindQuestionsService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
        this.categoryPageService = new CategoryPageService();
    }
    @Override
    public List<Question> findCategoryAndTypeAndAmount(Integer categoryId, String type, int amount) {

        int currentPage = categoryPageService.getCurrentPageForCategory(categoryId);

        List<Question> questions = questionRepository.findByCategoryAndTypeAndAmountByNumberPage(categoryId, type, amount, currentPage);

        if (!questions.isEmpty())
            categoryPageService.incrementPageForCategory(categoryId);
        else
            categoryPageService.resetPageForCategory(categoryId);

        return questions;
    }
}
