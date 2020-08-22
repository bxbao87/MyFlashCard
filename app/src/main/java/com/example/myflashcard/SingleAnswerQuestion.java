package com.example.myflashcard;

import android.graphics.Bitmap;

public class SingleAnswerQuestion extends Question {
    String answer;

    public SingleAnswerQuestion(String qname, String hint, String key, Bitmap questionImage, boolean isImageQuestion, String answer) {
        super(qname, hint, key, questionImage, isImageQuestion);
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean checkAnswer(String answer) {
        return false;
    }
}
