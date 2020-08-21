package com.example.myflashcard;

import java.util.ArrayList;

enum questionType {
    MULTIPLE_CHOICE,
    SINGLE_ANSWER;
}

public class Question {
    questionType type;
    String name;
    String hint;
    String Key;                 //correct answer to check with

    public Question(questionType type, String name, String hint, String key) {
        this.type = type;
        this.name = name;
        this.hint = hint;
        this.Key = key;
    }
}