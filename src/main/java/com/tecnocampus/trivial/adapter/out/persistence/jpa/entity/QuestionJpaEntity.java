package com.tecnocampus.trivial.adapter.out.persistence.jpa.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Question")
public class QuestionJpaEntity {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryJpaEntity category;
    private String dificult;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "question_answers", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id")
    )
    private Set<AnswerJpaEntity> answers = new HashSet<>();
    private String questionDescription;
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CategoryJpaEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryJpaEntity category) {
        this.category = category;
    }

    public String getDificult() {
        return dificult;
    }

    public void setDificult(String dificult) {
        this.dificult = dificult;
    }

    public Set<AnswerJpaEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<AnswerJpaEntity> answers) {
        this.answers = answers;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addAnswer(AnswerJpaEntity answer) {
        answers.add(answer);
        answer.getQuestions().add(this);
    }

    public void removeAnswer(AnswerJpaEntity answer) {
        answers.remove(answer);
        answer.getQuestions().remove(this);
    }
}
