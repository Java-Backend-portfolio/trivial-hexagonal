package com.tecnocampus.trivial.model;

public class Answer {

    private Integer id;
    private String description;
    private boolean correct;

    public Answer(Integer id, String description, boolean correct) {
        this.id = id;
        this.description = description;
        this.correct = correct;
    }

    public Answer(String description, boolean correct) {
        this.description = description;
        this.correct = correct;
    }

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
}
