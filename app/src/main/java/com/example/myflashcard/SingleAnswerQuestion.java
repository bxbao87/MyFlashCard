package com.example.myflashcard;

import android.graphics.Bitmap;

import java.io.Serializable;

public class SingleAnswerQuestion extends Question implements Serializable {
    private String answer;

    public SingleAnswerQuestion(int type, String qname, String hint, String key, Bitmap questionImage, boolean isImageQuestion, String answer) {
        super(type, qname, hint, key, questionImage, isImageQuestion);
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
