package com.tecnocampus.trivial.adapter;

import com.tecnocampus.trivial.application.port.in.category.FindAllCategoriesUseCase;
import com.tecnocampus.trivial.application.port.in.question.FindQuestionsUseCase;
import com.tecnocampus.trivial.application.port.out.api.category.DownloadCategoriesApi;
import com.tecnocampus.trivial.application.port.out.api.question.DownloadQuestionsApi;
import com.tecnocampus.trivial.application.port.out.persistence.CategoryRepository;
import com.tecnocampus.trivial.application.port.out.persistence.QuestionRepository;
import com.tecnocampus.trivial.application.service.category.FindAllCategoriesService;
import com.tecnocampus.trivial.application.service.init.InitAppService;
import com.tecnocampus.trivial.application.service.question.FindQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAppConfig {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    DownloadCategoriesApi downloadCategoriesApi;

    @Autowired
    DownloadQuestionsApi downloadQuestionsApi;

    @Bean
    FindAllCategoriesUseCase findAllCategoriesUseCase() {
        return new FindAllCategoriesService(categoryRepository);
    }

    @Bean
    FindQuestionsUseCase findQuestionsUseCase() {
        return new FindQuestionsService(questionRepository);
    }

    @Bean
    InitAppService initApp() {
        return new InitAppService(questionRepository, downloadQuestionsApi, categoryRepository, downloadCategoriesApi);
    }
}
