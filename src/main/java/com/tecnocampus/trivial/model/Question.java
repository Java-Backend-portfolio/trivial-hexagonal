package com.tecnocampus.trivial.model;

import com.tecnocampus.trivial.adapter.out.persistence.jpa.entity.AnswerJpaEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question {

    private Integer id;
    private Category category;
    private String dificult;
    private Set<Answer> answers;
    private String question;
    private String type;

    public Question(Integer id, String dificult, String question, String type) {
        this.id = id;
        this.dificult = dificult;
        this.question = question;
        this.type = type;
    }

    public Question(String dificult, String question, String type) {
        this.dificult = dificult;
        this.question = question;
        this.type = type;
    }

    public void addAnswer(Answer answer){
        if (this.answers == null)
            this.answers = new HashSet<>();

        answers.add(answer);
    }

    public void removeAnswer(Answer answer) {
        answers.remove(answer);
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getDificult() {
        return dificult;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public String getQuestion() {
        return question;
    }

    public String getType() {
        return type;
    }
}
