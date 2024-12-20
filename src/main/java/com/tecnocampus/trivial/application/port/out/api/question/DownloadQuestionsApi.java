package com.tecnocampus.trivial.application.port.out.api.question;

import com.tecnocampus.trivial.model.Question;

import java.util.List;

public interface DownloadQuestionsApi {

    List<Question> downloadQuestions(String categoryId);
}
