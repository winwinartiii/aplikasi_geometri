package com.example.bangunruang;

public class NilaiItem {
    private String name;
    private String className;
    private Integer score;

    public NilaiItem(String name, String className, Integer score) {
        this.name = name;
        this.className = className;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
