package com.tecnocampus.trivial.adapter.out.persistence.jpa.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Answer")
public class AnswerJpaEntity {

    @Id
    @GeneratedValue
    private Integer id;
    private String description;
    @Column(name = "correct", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean correct;
    @ManyToMany(mappedBy = "answers")
    private Set<QuestionJpaEntity> questions = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Set<QuestionJpaEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<QuestionJpaEntity> questions) {
        this.questions = questions;
    }
}
