package com.tecnocampus.trivial.adapter.out.rest.entityDTO;

import java.util.List;

public record QuestionApiDTO(String category, String correct_answer, String difficulty, List<String> incorrect_answers, String question, String type) {

}
