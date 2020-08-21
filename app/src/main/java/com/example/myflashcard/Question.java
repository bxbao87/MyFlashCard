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

    public Question(questionType type, String name, String hint) {
        this.type = type;
        this.name = name;
        this.hint = hint;
    }
}