package com.tecnocampus.trivial.application.service.init;

import com.tecnocampus.trivial.application.port.out.api.category.DownloadCategoriesApi;
import com.tecnocampus.trivial.application.port.out.api.category.DownloadCategoriesUseCase;
import com.tecnocampus.trivial.application.port.out.api.question.DownloadQuestionsApi;
import com.tecnocampus.trivial.application.port.out.api.question.DownloadQuestionsUseCase;
import com.tecnocampus.trivial.application.port.out.persistence.CategoryRepository;
import com.tecnocampus.trivial.application.port.out.persistence.QuestionRepository;
import com.tecnocampus.trivial.model.Category;
import com.tecnocampus.trivial.model.Question;
import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InitAppService implements DownloadQuestionsUseCase, DownloadCategoriesUseCase {

    private final QuestionRepository questionRepository;
    private final DownloadQuestionsApi downloadQuestionsApi;
    private final CategoryRepository categoryRepository;
    private final DownloadCategoriesApi downloadCategoriesApi;
    private List<Category> categories;

    public InitAppService(QuestionRepository questionRepository, DownloadQuestionsApi downloadQuestionsApi, CategoryRepository categoryRepository, DownloadCategoriesApi downloadCategoriesApi) {
        this.questionRepository = questionRepository;
        this.downloadQuestionsApi = downloadQuestionsApi;
        this.categoryRepository = categoryRepository;
        this.downloadCategoriesApi = downloadCategoriesApi;
        categories = new ArrayList<>();
    }

    @PostConstruct
    public void onApplicationStart() {
        System.out.println("Ejecutando método al inicio con @PostConstruct...");
        downloadCategories();
        downloadQuestions();
    }

    @Override
    public void downloadCategories() {
        categories = downloadCategoriesApi.downloadCategories();

        // Comentar de la linea 48 a la 51 si se quiere guardar todas las categorías de la API que consultamos
        // IMPORTANTE!!! Hacer esto hará que tarde en arrancar la aplicación.

        categories = categories.stream()
                                .unordered()  // Garantiza que no se preserve el orden
                                .limit(5)     // Limita el número de elementos
                                .collect(Collectors.toList());

        categories.stream().forEach(c -> categoryRepository.save(c));
    }

    @Override
    public void downloadQuestions() {
        List<Question> questions;

        List<Category> categoriesToQuestions = categoryRepository.findAll();

        for (Category category : categories) {
            questions = downloadQuestionsApi.downloadQuestions(category.getId().toString());

            questions.stream()
                    .map(q -> setCategoryId(q, category, categoriesToQuestions))
                    .forEach(q -> questionRepository.save(q));

        }

    }

    private Question setCategoryId(Question question, Category categoryApi, List<Category> categories) {

        Optional<Category> opCategory = categories.stream().filter(c -> c.getTitle().equals(categoryApi.getTitle())).findFirst();
        if (opCategory.isPresent()) {
            question.getCategory().setId(opCategory.get().getId());
        }

        return question;
    }
}
