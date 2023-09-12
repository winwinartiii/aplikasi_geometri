package com.example.bangunruang;

import java.util.List;

public class KuisItem {
    private String question;
    private List<String> options;
    private int correctAnswerId;
    private int answerId;

    public KuisItem(String question, List<String> options, int correctAnswerId) {
        this.question = question;
        this.options = options;
        this.correctAnswerId = correctAnswerId;
        this.answerId = 0;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getCorrectAnswerId() {
        return correctAnswerId;
    }

    public void setCorrectAnswerId(int correctAnswerId) {
        this.correctAnswerId = correctAnswerId;
    }
}
