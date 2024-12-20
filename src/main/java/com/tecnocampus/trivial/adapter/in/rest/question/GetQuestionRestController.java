package com.tecnocampus.trivial.adapter.in.rest.question;

import com.tecnocampus.trivial.adapter.in.rest.question.webmodel.QuestionDTO;
import com.tecnocampus.trivial.application.port.in.question.FindQuestionsUseCase;
import com.tecnocampus.trivial.model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.tecnocampus.trivial.adapter.in.rest.common.ControllerCommons.clientErrorException;

@RestController
@CrossOrigin
@RequestMapping("/categories")
public class GetQuestionRestController {

    private final FindQuestionsUseCase findQuestionsUseCase;

    public GetQuestionRestController(FindQuestionsUseCase findQuestionsUseCase) {
        this.findQuestionsUseCase = findQuestionsUseCase;
    }

    @GetMapping("/{categoryId}/questions")
    public List<QuestionDTO> getQuestions(@PathVariable Integer categoryId, @RequestParam(required = false, defaultValue = "10") int limit) {

        List<Question> questions;

        try {
            questions = findQuestionsUseCase.findCategoryAndTypeAndAmount(categoryId, "multiple",  limit);
        } catch (IllegalArgumentException e) {
            throw clientErrorException(HttpStatus.NOT_FOUND, "Questions not found'");
        }
        return questions.stream().map(QuestionDTO::fromDomainModel).toList();
    }
}

